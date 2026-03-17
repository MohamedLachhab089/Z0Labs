package org.med.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.med.kafka.OrderConfirmation;

public class OrderConfirmationSerializer implements Serializer<OrderConfirmation> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(String topic, OrderConfirmation data) {
    try {
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new RuntimeException("Error serializing OrderConfirmation", e);
    }
  }
}
