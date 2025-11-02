package iuh.fit.se.trancongminh_week07.controller;


import iuh.fit.se.trancongminh_week07.entities.Comment;
import iuh.fit.se.trancongminh_week07.services.CommentService;
import iuh.fit.se.trancongminh_week07.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ProductService productService;

    //Thêm bình luận mới (được gọi từ form trong productdetail.html)
    @PostMapping("/add/{productId}")
    public String addComment(@PathVariable Integer productId,
                             @ModelAttribute Comment comment) {
        commentService.create(productId, comment);
        return "redirect:/product/" + productId;
    }

    //Hiển thị form sửa bình luận
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editCommentForm(@PathVariable Integer id, Model model) {
        Comment comment = commentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận ID = " + id));
        model.addAttribute("comment", comment);
        return "comment/edit";
    }

    // 🟡 Cập nhật bình luận
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateComment(@PathVariable Integer id,
                                @ModelAttribute Comment updatedComment) {
        Comment c = commentService.update(id, updatedComment);
        return "redirect:/product/" + c.getProduct().getId();
    }

    //Xóa bình luận
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Integer id) {
        Comment comment = commentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận ID = " + id));
        Integer productId = comment.getProduct().getId();
        commentService.deleteById(id);
        return "redirect:/product/" + productId;
    }
}
