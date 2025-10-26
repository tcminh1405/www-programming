package iuh.fit.se.trancongminh_week07.reposities;

import iuh.fit.se.trancongminh_week07.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUserUsername(String username);
}