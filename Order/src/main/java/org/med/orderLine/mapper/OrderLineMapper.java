package org.med.orderLine.mapper;

import org.med.order.model.Order;
import org.med.orderLine.dto.OrderLineRequest;
import org.med.orderLine.dto.OrderLineResponse;
import org.med.orderLine.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

  public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
    if (orderLineRequest == null) {
      return null;
    }
    return OrderLine.builder()
        .id(orderLineRequest.id())
        .productId(orderLineRequest.productId())
        .order(Order.builder().id(orderLineRequest.orderId()).build())
        .quantity(orderLineRequest.quantity())
        .build();
  }

  public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
    return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
  }
}
