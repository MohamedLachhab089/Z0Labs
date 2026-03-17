package org.med.order.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.med.order.dto.OrderRequest;
import org.med.order.dto.OrderResponse;
import org.med.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/{order-id}")
  public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId) {
    return ResponseEntity.ok(orderService.getOrderById(orderId));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  @PostMapping
  public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
    return ResponseEntity.ok(orderService.createOrder(request));
  }
}
