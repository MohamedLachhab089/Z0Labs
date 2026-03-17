package org.med.kafka;

import java.math.BigDecimal;
import java.util.List;
import org.med.customer.CustomerResponse;
import org.med.order.enums.PaymentMethod;
import org.med.product.PurchaseResponse;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products) {}
