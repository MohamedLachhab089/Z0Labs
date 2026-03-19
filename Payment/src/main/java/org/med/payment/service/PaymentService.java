package org.med.payment.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.med.payment.dto.PaymentNotificationRequest;
import org.med.payment.dto.PaymentRequest;
import org.med.payment.kafka.notification.NotificationProducer;
import org.med.payment.mapper.PaymentMapper;
import org.med.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper paymentMapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(@Valid PaymentRequest paymentRequest) {
    var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
    notificationProducer.sendNotification(
        new PaymentNotificationRequest(
            paymentRequest.orderReference(),
            paymentRequest.amount(),
            paymentRequest.paymentMethod(),
            paymentRequest.customer().firstname(),
            paymentRequest.customer().lastname(),
            paymentRequest.customer().email()));
    return payment.getId();
  }
}
