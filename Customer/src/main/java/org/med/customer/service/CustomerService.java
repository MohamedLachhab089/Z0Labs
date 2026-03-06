package org.med.customer.service;

import lombok.RequiredArgsConstructor;
import org.med.customer.dto.CustomerRequest;
import org.med.customer.exception.CustomerNotFoundException;
import org.med.customer.mapper.CustomerMapper;
import org.med.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  public final CustomerRepository customerRepository;
  public final CustomerMapper customerMapper;

  public String createCustomer(CustomerRequest customerRequest) {
    var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
    return customer.getId();
  }

  public void updateCustomer(CustomerRequest customerRequest) {
    var customer =
        customerRepository
            .findById(customerRequest.id())
            .orElseThrow(
                () -> new CustomerNotFoundException("Cannot update Customer :: No Customer found with id: " + customerRequest.id()));
    customerMapper.mergeCustomer(customer, customerRequest);
    customerRepository.save(customer);
  }
}
