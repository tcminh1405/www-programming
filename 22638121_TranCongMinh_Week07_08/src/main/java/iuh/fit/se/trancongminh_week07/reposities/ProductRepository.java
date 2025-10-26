package iuh.fit.se.trancongminh_week07.reposities;

import iuh.fit.se.trancongminh_week07.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Integer> {
}
