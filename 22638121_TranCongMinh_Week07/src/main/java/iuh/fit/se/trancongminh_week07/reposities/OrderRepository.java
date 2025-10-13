package iuh.fit.se.trancongminh_week07.reposities;

import iuh.fit.se.trancongminh_week07.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
