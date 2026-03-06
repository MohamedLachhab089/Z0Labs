package org.med.customer.mapper;

import org.apache.commons.lang3.StringUtils;
import org.med.customer.dto.CustomerRequest;
import org.med.customer.dto.CustomerResponse;
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

  public CustomerResponse toCustomerResponse(Customer customer) {
    if (customer == null) {
      return null;
    }
    return new CustomerResponse(
        customer.getId(),
        customer.getFirstname(),
        customer.getLastname(),
        customer.getEmail(),
        customer.getAddress());
  }

  // This is used to merge the customer during the update
  public void mergeCustomer(Customer customer, CustomerRequest request) {
    if (StringUtils.isNotBlank(request.firstName())) {
      customer.setFirstname(request.firstName());
    }
    if (StringUtils.isNotBlank(request.lastName())) {
      customer.setLastname(request.lastName());
    }
    if (StringUtils.isNotBlank(request.email())) {
      customer.setEmail(request.email());
    }
    if (request.address() != null) {
      customer.setAddress(request.address());
    }
  }
}
