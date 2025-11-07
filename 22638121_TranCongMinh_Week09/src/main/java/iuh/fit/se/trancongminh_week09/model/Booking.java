package iuh.fit.se.trancongminh_week09.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName; //họ

    @Column(name = "last_name", nullable = false)
    private String lastName; // tên

    @Column(name = "from_location", nullable = false)
    private String from; // Điểm đi

    @Column(name = "to_location", nullable = false)
    private String to; // Điểm đến

    @Column(name = "flight_date", nullable = false)
    private String date; // Ngày bay (yyyy-MM-dd)

    @Column(name = "passenger_count")
    private Integer passengerCount; // Số hành khách

    @Column(name = "seat_class")
    private String seatClass; // Hạng ghế (Economy, Business, ...)
}
