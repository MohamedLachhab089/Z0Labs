package org.med.payment.mapper;

import org.med.payment.dto.PaymentRequest;
import org.med.payment.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

  public Payment toPayment(PaymentRequest paymentRequest) {
    if (paymentRequest == null) {
      return null;
    }
    return Payment.builder()
        .id(paymentRequest.id())
        .amount(paymentRequest.amount())
        .paymentMethod(paymentRequest.paymentMethod())
        .orderId(paymentRequest.orderId())
        .build();
  }
}
