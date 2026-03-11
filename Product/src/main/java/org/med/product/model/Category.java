package org.med.product.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
  @Id @GeneratedValue private Integer id;
  private String name;
  private String description;

  @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
  private List<Product> products;
}
