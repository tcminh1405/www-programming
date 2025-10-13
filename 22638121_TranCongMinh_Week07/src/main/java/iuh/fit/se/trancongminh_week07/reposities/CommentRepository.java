package iuh.fit.se.trancongminh_week07.reposities;

import iuh.fit.se.trancongminh_week07.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByProductId(Integer productId);
}
