package iuh.fit.se.trancongminh_week09.service;

import iuh.fit.se.trancongminh_week09.model.Booking;
import iuh.fit.se.trancongminh_week09.repository.BookingRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AiService {

    private static final Logger logger = LoggerFactory.getLogger(AiService.class);

    private static final String GEMINI_API_URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=%s";

    private final BookingRepository bookingRepository;
    private final String apiKey;

    public AiService(
            BookingRepository bookingRepository,
            @Value("${GEMINI_API_KEY}") String geminiApiKey) {
        this.bookingRepository = bookingRepository;
        this.apiKey = geminiApiKey;
    }

    /**
     * Xử lý câu hỏi người dùng - AI hiểu và tự động tìm thông tin từ DB nếu có
     */
    public String processUserInput(String input) {
        logger.info("🧠 Processing user input: {}", input);
        try {
            // 1️⃣ Dùng AI để trích xuất thông tin (tên + mã booking)
            String extractPrompt = String.format("""
                Bạn là trợ lý vé máy bay.
                        Khi nhận tên người Việt Nam, **đừng đảo ngược thứ tự họ tên**.
                        Hãy xuất đúng cấu trúc:
                        {
                          "firstName": "Tran",
                          "lastName": "Cong Minh"
                        }
                Từ câu hỏi sau, hãy tìm và trả về JSON với:
                {
                  "bookingNumber": "ABC123" hoặc null,
                  "firstName": "Nguyen" hoặc null,
                  "lastName": "Van A" hoặc null
                }
                Câu hỏi: "%s"
                Trả về đúng JSON, không có văn bản khác.
            """, input);

            String extractResponse = callGemini(extractPrompt);
            logger.info("🧩 AI extraction response: {}", extractResponse);

            String bookingNumber = extractJsonValue(extractResponse, "bookingNumber");
            String firstName = extractJsonValue(extractResponse, "firstName");
            String lastName = extractJsonValue(extractResponse, "lastName");

            logger.info("➡️ Extracted bookingNumber={}, firstName={}, lastName={}",
                    bookingNumber, firstName, lastName);

            // 2️⃣ Nếu có dữ liệu tên hoặc mã vé → tra DB
            if (firstName != null && lastName != null) {
                List<Booking> bookings = bookingRepository.findAll();

                for (Booking b : bookings) {
                    if (b.getFirstName().equalsIgnoreCase(firstName)
                            && b.getLastName().equalsIgnoreCase(lastName)) {
                        return String.format("""
                                ✅ Thông tin đặt vé:
                                • Hành khách: %s %s
                                • Từ: %s
                                • Đến: %s
                                • Ngày bay: %s
                                • Số hành khách: %d
                                • Hạng ghế: %s
                                """,
                                b.getFirstName(), b.getLastName(),
                                b.getFrom(), b.getTo(),
                                b.getDate(), b.getPassengerCount(),
                                b.getSeatClass());
                    }
                }

                return String.format("❌ Không tìm thấy thông tin đặt vé cho hành khách %s %s.",
                        firstName, lastName);
            }

            // 3️⃣ Nếu không có thông tin cụ thể → để AI trả lời bình thường
            String aiResponse = callGemini(input);
            return "🤖 " + aiResponse;

        } catch (Exception e) {
            logger.error("❌ Lỗi xử lý: {}", e.getMessage(), e);
            return "⚠️ Đã xảy ra lỗi khi xử lý yêu cầu.";
        }
    }

    /**
     * Gọi Gemini API với prompt cho sẵn
     */
    public String callGemini(String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            JSONObject requestBody = new JSONObject()
                    .put("contents", new JSONArray()
                            .put(new JSONObject()
                                    .put("parts", new JSONArray()
                                            .put(new JSONObject()
                                                    .put("text", prompt)))));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
            String url = String.format(GEMINI_API_URL, apiKey);

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, String.class);

            JSONObject jsonResponse = new JSONObject(response.getBody());

            return jsonResponse
                    .getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");

        } catch (Exception e) {
            logger.error("Gemini API error: {}", e.getMessage());
            return "❌ Gemini API error: " + e.getMessage();
        }
    }

    /**
     * Hàm nhỏ để trích xuất giá trị JSON đơn giản từ text AI trả về
     */
    private String extractJsonValue(String json, String key) {
        try {
            Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(json);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        } catch (Exception e) {
            logger.warn("⚠️ JSON parse error for key {}: {}", key, e.getMessage());
        }
        return null;
    }
}
