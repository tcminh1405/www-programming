package iuh.fit.se.trancongminh_week07.reposities;

import iuh.fit.se.trancongminh_week07.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByOrderId(Integer orderId);
}
