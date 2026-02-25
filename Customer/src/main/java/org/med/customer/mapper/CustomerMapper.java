package org.med.customer.mapper;

import org.med.customer.dto.CustomerRequest;
import org.med.customer.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest customerRequest) {
    if (customerRequest == null) {
      return null;
    }
    return Customer.builder()
        .id(customerRequest.id())
        .firstname(customerRequest.firstName())
        .lastname(customerRequest.lastName())
        .email(customerRequest.email())
        .address(customerRequest.address())
        .build();
  }

  public CustomerRequest toCustomerRequest(Customer customer) {
    if (customer == null) {
      return null;
    }
    return new CustomerRequest(
        customer.getId(),
        customer.getFirstname(),
        customer.getLastname(),
        customer.getEmail(),
        customer.getAddress());
  }
}
