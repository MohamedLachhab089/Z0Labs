package org.med.orderLine.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.med.orderLine.dto.OrderLineResponse;
import org.med.orderLine.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

  private final OrderLineService orderLineService;

  @GetMapping("/order/{order-id}")
  public ResponseEntity<List<OrderLineResponse>> findByOrderId(
      @PathVariable("order-id") Integer orderId) {
    return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
  }
}
