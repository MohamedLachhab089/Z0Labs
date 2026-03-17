package org.med.orderLine.model;

import jakarta.persistence.*;
import lombok.*;
import org.med.order.model.Order;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer_line")
public class OrderLine {
  @Id @GeneratedValue private Integer id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  private Integer productId;
  private double quantity;
}
