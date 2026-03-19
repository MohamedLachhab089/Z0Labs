package org.med.payment;

import java.math.BigDecimal;
import org.med.customer.CustomerResponse;
import org.med.order.enums.PaymentMethod;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer) {}
