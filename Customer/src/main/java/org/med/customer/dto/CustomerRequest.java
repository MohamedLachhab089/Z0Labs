package org.med.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.med.customer.model.Address;

public record CustomerRequest(
    String id,
    @NotNull(message = "Customer Firstname is required") String firstName,
    @NotNull(message = "Customer Lastname is required") String lastName,
    @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is not valid email address")
        String email,
    Address address) {}
