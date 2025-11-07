package iuh.fit.se.trancongminh_week09.controller;

import iuh.fit.se.trancongminh_week09.service.AiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final AiService aiService;

    /**
     * Ví dụ: http://localhost:8090/chat/ask?q=Cho tôi xem vé của Tran Thi B
     */
    @GetMapping("/ask")
    public String askGemini(@RequestParam String q) {
        logger.info("📩 User question: {}", q);
        String response = aiService.processUserInput(q);
        logger.info("🤖 AI response: {}", response);
        return response;
    }

    /**
     * Gọi bằng POST
     */
    @PostMapping("/ask")
    public String askGeminiPost(@RequestBody ChatRequest request) {
        logger.info("📩 User POST question: {}", request.getMessage());
        String response = aiService.processUserInput(request.getMessage());
        logger.info("🤖 AI response: {}", response);
        return response;
    }

    // DTO nhỏ cho POST request
    public static class ChatRequest {
        private String message;
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
