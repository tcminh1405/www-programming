package iuh.fit.se.trancongminh_week09.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import iuh.fit.se.trancongminh_week09.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Giao diện trợ lý đặt vé máy bay sử dụng Gemini AI
 */
@Route("")
public class MainView extends VerticalLayout {

    private final AiService aiService;

    private Div chatContainer;
    private TextField inputField;
    private Button sendButton;
    private Paragraph statusText;

    @Autowired
    public MainView(AiService aiService) {
        this.aiService = aiService;
        setupLayout();
    }

    private void setupLayout() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("background-color", "#f4f6fa");

        // Tiêu đề
        H1 title = new H1("✈️ Gemini Flight Booking Assistant");
        title.getStyle()
                .set("color", "#0077cc")
                .set("font-family", "Segoe UI, sans-serif")
                .set("margin-bottom", "0.2em");

        Span subtitle = new Span("Trò chuyện với Gemini để hỏi thông tin chuyến bay, giá vé và lịch trình*");
        subtitle.getStyle()
                .set("color", "#555")
                .set("font-size", "14px")
                .set("margin-bottom", "1em");

        // Khung hội thoại
        chatContainer = new Div();
        chatContainer.setWidth(800, Unit.PIXELS);
        chatContainer.setHeight(450, Unit.PIXELS);
        chatContainer.getStyle()
                .set("background-color", "white")
                .set("border-radius", "12px")
                .set("box-shadow", "0 2px 8px rgba(0,0,0,0.1)")
                .set("overflow-y", "auto")
                .set("padding", "16px")
                .set("display", "flex")
                .set("flex-direction", "column");

        // Thanh nhập liệu
        inputField = new TextField();
        inputField.setPlaceholder("Nhập câu hỏi của bạn về chuyến bay...");
        inputField.setWidth(650, Unit.PIXELS);
        inputField.getStyle()
                .set("border-radius", "8px")
                .set("background-color", "#f8fafc");

        sendButton = new Button("Gửi 🚀");
        sendButton.getStyle()
                .set("background-color", "#0077cc")
                .set("color", "white")
                .set("border-radius", "8px")
                .set("font-weight", "600")
                .set("padding", "0.6em 1.2em");
        sendButton.addClickListener(e -> handleSendMessage());

        HorizontalLayout inputLayout = new HorizontalLayout(inputField, sendButton);
        inputLayout.setAlignItems(Alignment.BASELINE);
        inputLayout.setSpacing(true);

        statusText = new Paragraph("💡 Trợ lý đã sẵn sàng.");
        statusText.getStyle().set("color", "gray").set("font-size", "13px");

        add(title, subtitle, chatContainer, inputLayout, statusText);
    }

    private void handleSendMessage() {
        String userMessage = inputField.getValue().trim();
        if (userMessage.isEmpty()) {
            Notification.show("❗ Vui lòng nhập nội dung câu hỏi");
            return;
        }

        // 👤 Tin nhắn người dùng (bên trái)
        addChatBubble(userMessage, true);
        statusText.setText("⏳ Đang gửi yêu cầu đến Gemini...");

        try {
            // 🤖 Phản hồi từ Gemini (bên phải)
            String aiResponse = aiService.processUserInput(userMessage);
            addChatBubble(aiResponse, false);
            statusText.setText("✅ Gemini đã phản hồi.");
        } catch (Exception e) {
            addChatBubble("Lỗi khi gọi API: " + e.getMessage(), false);
            statusText.setText("⚠️ Lỗi khi gọi API.");
        }

        inputField.clear();
    }

    /**
     * Thêm bong bóng chat (User trái, Gemini phải)
     */
    private void addChatBubble(String message, boolean isUser) {
        // Bọc bong bóng trong layout ngang
        HorizontalLayout messageLayout = new HorizontalLayout();
        messageLayout.setWidthFull();
        messageLayout.setPadding(false);

        // Bong bóng nội dung
        Div bubble = new Div();
        bubble.setText(message);
        bubble.getStyle()
                .set("padding", "10px 14px")
                .set("margin", "8px")
                .set("border-radius", "12px")
                .set("max-width", "65%")
                .set("white-space", "pre-wrap")
                .set("font-family", "Segoe UI, sans-serif")
                .set("line-height", "1.5");

        if (isUser) {
            // 👤 Người dùng (bên trái)
            bubble.getStyle()
                    .set("background-color", "#d1e9ff")
                    .set("color", "#003366");
            messageLayout.setJustifyContentMode(JustifyContentMode.START);
        } else {
            // 🤖 Gemini (bên phải)
            bubble.getStyle()
                    .set("background-color", "#e9ecef")
                    .set("color", "#222");
            messageLayout.setJustifyContentMode(JustifyContentMode.END);
        }

        messageLayout.add(bubble);
        chatContainer.add(messageLayout);

        // Tự động cuộn xuống cuối
        chatContainer.getElement().executeJs("this.scrollTop = this.scrollHeight");
    }
}
