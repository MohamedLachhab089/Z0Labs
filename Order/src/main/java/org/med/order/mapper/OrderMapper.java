package org.med.order.mapper;

import org.med.order.dto.OrderRequest;
import org.med.order.dto.OrderResponse;
import org.med.order.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

  public Order toOrder(OrderRequest orderRequest) {
    if (orderRequest == null) {
      return null;
    }
    return Order.builder()
        .id(orderRequest.id())
        .reference(orderRequest.reference())
        .paymentMethod(orderRequest.paymentMethod())
        .customerId(orderRequest.customerId())
        .build();
  }

  public OrderResponse toOrderResponse(Order order) {
    if (order == null) {
      return null;
    }
    return new OrderResponse(
        order.getId(),
        order.getReference(),
        order.getTotalAmount(),
        order.getPaymentMethod(),
        order.getCustomerId());
  }
}
