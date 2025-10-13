package iuh.fit.se.trancongminh_week07.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orderlines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Many-to-One: Nhiều OrderLine thuộc về 1 Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer amount;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    // Many-to-One: Nhiều OrderLine thuộc về 1 Order
    // Thuộc tính này tham chiếu đến Order, khóa ngoại là order_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
