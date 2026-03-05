package org.med.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.med.customer.dto.CustomerRequest;
import org.med.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<String> createCustomer(
      @RequestBody @Valid CustomerRequest customerRequest) {
    return ResponseEntity.ok(customerService.createCustomer(customerRequest));
  }

  @PutMapping
  public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
    customerService.updateCustomer(customerRequest);
    return ResponseEntity.accepted().build();
  }
}
