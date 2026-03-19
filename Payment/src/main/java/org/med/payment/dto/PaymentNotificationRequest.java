package org.med.payment.dto;

import java.math.BigDecimal;
import org.med.payment.enums.PaymentMethod;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstname,
    String customerLastname,
    String customerEmail) {}
