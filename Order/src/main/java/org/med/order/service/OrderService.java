package org.med.order.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.med.customer.CustomerClient;
import org.med.kafka.OrderConfirmation;
import org.med.kafka.OrderProducer;
import org.med.order.dto.OrderRequest;
import org.med.order.dto.OrderResponse;
import org.med.order.exception.BusinessException;
import org.med.order.mapper.OrderMapper;
import org.med.order.repository.OrderRepository;
import org.med.orderLine.dto.OrderLineRequest;
import org.med.orderLine.service.OrderLineService;
import org.med.payment.PaymentClient;
import org.med.payment.PaymentRequest;
import org.med.product.ProductClient;
import org.med.product.PurchaseRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final CustomerClient customerClient;
  private final ProductClient productClient;
  private final OrderLineService orderLineService;
  private final OrderProducer orderProducer;
  private final PaymentClient paymentClient;

  public OrderResponse getOrderById(Integer id) {
    return orderRepository
        .findById(id)
        .map(orderMapper::toOrderResponse)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("Order with id %d not found", id)));
  }

  public List<OrderResponse> getAllOrders() {
    return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
  }

  public Integer createOrder(OrderRequest request) {
    var customer =
        customerClient
            .findCustomerById(request.customerId())
            .orElseThrow(
                () ->
                    new BusinessException("Cannot create order :: Customer with id %d not found"));
    var purchasedProducts = productClient.purchaseProducts(request.products());

    var order = orderRepository.save(orderMapper.toOrder(request));

    for (PurchaseRequest purchaseRequest : request.products()) {
      orderLineService.saveOrderLine(
          new OrderLineRequest(
              null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
    }

    var payment =
        new PaymentRequest(
            request.amount(),
            request.paymentMethod(),
            order.getId(),
            order.getReference(),
            customer);
    paymentClient.requestOrderPayment(payment);

    orderProducer.sendOrderConfirmation(
        new OrderConfirmation(
            request.reference(),
            request.amount(),
            request.paymentMethod(),
            customer,
            purchasedProducts));

    return order.getId();
  }
}
