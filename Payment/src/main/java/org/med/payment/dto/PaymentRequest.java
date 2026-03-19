package org.med.payment.dto;

import java.math.BigDecimal;
import org.med.payment.enums.PaymentMethod;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    Customer customer) {}
