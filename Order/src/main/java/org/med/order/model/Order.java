package org.med.order.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.med.order.enums.PaymentMethod;
import org.med.orderLine.model.OrderLine;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {
  @Id @GeneratedValue private Integer id;

  @Column(unique = true, nullable = false)
  private String reference;

  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private String customerId;

  @OneToMany(mappedBy = "order")
  private List<OrderLine> orderLines;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime LastModifiedDate;
}
