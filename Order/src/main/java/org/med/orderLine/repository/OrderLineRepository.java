package org.med.orderLine.repository;

import java.util.List;
import org.med.orderLine.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
  List<OrderLine> findAllByOrderId(Integer orderId);
}
