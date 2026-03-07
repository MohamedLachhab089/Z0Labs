package org.med.customer.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.med.customer.dto.CustomerRequest;
import org.med.customer.dto.CustomerResponse;
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
                () ->
                    new CustomerNotFoundException(
                        "Cannot update Customer :: No Customer found with id: "
                            + customerRequest.id()));
    customerMapper.mergeCustomer(customer, customerRequest);
    customerRepository.save(customer);
  }

  public List<CustomerResponse> getAllCustomers() {
    return customerRepository.findAll().stream().map(customerMapper::toCustomerResponse).toList();
  }

  public CustomerResponse findCustomerById(String id) {
    return customerRepository
        .findById(id)
        .map(customerMapper::toCustomerResponse)
        .orElseThrow(
            () ->
                new CustomerNotFoundException(String.format("No Customer found with id: %s", id)));
  }

  public boolean existsCustomerById(String id) {
    return customerRepository.findById(id).isPresent();
  }

  public void deleteCustomerById(String id) {
    if (!customerRepository.existsById(id)) {
      throw new CustomerNotFoundException(
          String.format("Deletion denied :: No Customer found with id: %s", id));
    }
    customerRepository.deleteById(id);
  }
}
