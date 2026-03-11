package org.med.product.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  @Id @GeneratedValue private Integer id;
  private String name;
  private String description;
  private Double availableQuantity;
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
