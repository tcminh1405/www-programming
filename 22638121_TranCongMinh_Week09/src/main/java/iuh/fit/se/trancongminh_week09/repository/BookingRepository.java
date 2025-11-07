package iuh.fit.se.trancongminh_week09.repository;

import iuh.fit.se.trancongminh_week09.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
