package org.med.product.repository;

import java.util.List;
import org.med.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findAllByIdInOrderById(List<Integer> ids);
}
