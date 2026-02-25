package org.med.customer.dto;

import org.med.customer.model.Address;

public record CustomerResponse(
    String id, String firstname, String lastname, String email, Address address) {}
