package iuh.fit.se.trancongminh_week07.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private BigDecimal price;

    @Column(name = "in_stock")
    private boolean inStock;

    // One-to-Many: 1 Product có nhiều Comment
    // MappedBy trỏ đến tên thuộc tính Product trong lớp Comment
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // comments: List<Comment>

    // One-to-Many: 1 Product có nhiều OrderLine
    // MappedBy trỏ đến tên thuộc tính 'product' trong lớp OrderLine
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLine> orderLines;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}