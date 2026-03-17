package org.med.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import org.med.order.enums.PaymentMethod;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId) {}
