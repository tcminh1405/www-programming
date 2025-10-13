package iuh.fit.se.trancongminh_week07.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "customer_since")
    private LocalDate customerSince;

    // One-to-Many: 1 Customer có nhiều Order
    // mappedBy trỏ đến tên thuộc tính Customer trong lớp Order
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;
}
