package iuh.fit.se.trancongminh_week07.services;

import iuh.fit.se.trancongminh_week07.entities.Comment;
import iuh.fit.se.trancongminh_week07.entities.Product;
import iuh.fit.se.trancongminh_week07.reposities.CommentRepository;
import iuh.fit.se.trancongminh_week07.reposities.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
        private final ProductRepository productRepository;

    // Lấy tất cả comment
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    // Lấy comment theo ID
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    // Lấy comment theo product_id
    public List<Comment> findByProductId(Integer productId) {
        return commentRepository.findByProductId(productId);
    }

    // Thêm mới comment
    public Comment create(Integer productId, Comment comment) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        comment.setProduct(product);
        return commentRepository.save(comment);
    }

    // Cập nhật comment
    public Comment update(Integer id, Comment newComment) {
        return commentRepository.findById(id)
                .map(existing -> {
                    existing.setText(newComment.getText());
                    if (newComment.getProduct() != null) {
                        existing.setProduct(newComment.getProduct());
                    }
                    return commentRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    // Xóa comment theo ID
    public void deleteById(Integer id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
