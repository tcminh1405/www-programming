package iuh.fit.se.trancongminh_week07.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    // Many-to-One: Nhiều Order cho 1 Customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // One-to-Many: 1 Order có nhiều OrderLine
    // MappedBy trỏ đến tên thuộc tính 'order' trong lớp OrderLine
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLine> orderLines; // orderLines: Set<OrderLine>
}