package iuh.fit.se.trancongminh_week09.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import iuh.fit.se.trancongminh_week09.model.Booking;
import iuh.fit.se.trancongminh_week09.repository.BookingRepository;
import iuh.fit.se.trancongminh_week09.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Route("bookings")
public class BookingView extends VerticalLayout {

    private final BookingRepository bookingRepository;
    private final AiService aiService;

    private final Grid<Booking> grid;
    private final TextField searchField;
    private final TextArea aiResponseArea;
    private final TextField aiInputField;

    @Autowired
    public BookingView(BookingRepository bookingRepository, AiService aiService) {
        this.bookingRepository = bookingRepository;
        this.aiService = aiService;

        this.grid = new Grid<>(Booking.class, false);
        this.searchField = new TextField();
        this.aiResponseArea = new TextArea("🧠 Gợi ý từ Gemini AI");
        this.aiInputField = new TextField();

        setupLayout();
        loadData();
    }

    private void setupLayout() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#f7f9fc");

        // Tiêu đề
        H1 title = new H1("📋 Danh sách đặt vé máy bay thông minh");
        title.getStyle()
                .set("color", "#0077cc")
                .set("font-family", "Segoe UI, sans-serif");

        // Tìm kiếm
        searchField.setPlaceholder("Tìm theo điểm đi hoặc điểm đến...");
        searchField.setWidth("400px");

        Button searchButton = new Button("🔍 Tìm kiếm", event -> searchBookings());
        Button resetButton = new Button("🔄 Làm mới", event -> loadData());

        HorizontalLayout searchLayout = new HorizontalLayout(searchField, searchButton, resetButton);
        searchLayout.setSpacing(true);

        // Bảng Booking
        grid.addColumn(Booking::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Booking::getFirstName).setHeader("Họ").setAutoWidth(true);
        grid.addColumn(Booking::getLastName).setHeader("Tên").setAutoWidth(true);
        grid.addColumn(Booking::getFrom).setHeader("Điểm đi").setAutoWidth(true);
        grid.addColumn(Booking::getTo).setHeader("Điểm đến").setAutoWidth(true);
        grid.addColumn(Booking::getDate).setHeader("Ngày bay").setAutoWidth(true);
        grid.addColumn(Booking::getPassengerCount).setHeader("Số hành khách").setAutoWidth(true);
        grid.addColumn(Booking::getSeatClass).setHeader("Hạng ghế").setAutoWidth(true);
        grid.setWidth("90%");
        grid.getStyle()
                .set("border-radius", "10px")
                .set("background-color", "white")
                .set("box-shadow", "0 2px 6px rgba(0,0,0,0.1)");

        // --- PHẦN AI GỢI Ý ---
        Label aiTitle = new Label("✈️ Trợ lý Gemini AI");
        aiTitle.getStyle()
                .set("font-weight", "600")
                .set("font-size", "16px")
                .set("color", "#0077cc");

        aiResponseArea.setWidth(800, Unit.PIXELS);
        aiResponseArea.setHeight(180, Unit.PIXELS);
        aiResponseArea.setReadOnly(true);
        aiResponseArea.getStyle()
                .set("background-color", "#f9f9f9")
                .set("border-radius", "8px")
                .set("font-family", "Segoe UI, sans-serif")
                .set("font-size", "14px");

        aiInputField.setPlaceholder("Hỏi Gemini: Ví dụ 'Tôi muốn bay từ Hà Nội đến Tokyo vào tháng 12'");
        aiInputField.setWidth(600, Unit.PIXELS);

        Button askAiButton = new Button("💡 Gợi ý từ AI", e -> askGemini());
        askAiButton.getStyle()
                .set("background-color", "#0077cc")
                .set("color", "white")
                .set("border-radius", "8px");

        HorizontalLayout aiInputLayout = new HorizontalLayout(aiInputField, askAiButton);
        aiInputLayout.setSpacing(true);
        aiInputLayout.setAlignItems(Alignment.BASELINE);

        // Thêm vào giao diện
        add(title, searchLayout, grid, new Hr(), aiTitle, aiInputLayout, aiResponseArea);
    }

    private void loadData() {
        grid.setItems(bookingRepository.findAll());
    }

    private void searchBookings() {
        String keyword = searchField.getValue().trim().toLowerCase();
        if (keyword.isEmpty()) {
            loadData();
            return;
        }

        List<Booking> filtered = bookingRepository.findAll().stream()
                .filter(b -> b.getFrom().toLowerCase().contains(keyword)
                        || b.getTo().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        grid.setItems(filtered);
    }

    /**
     * Gửi yêu cầu đến Gemini AI để gợi ý lịch trình
     */
    private void askGemini() {
        String question = aiInputField.getValue().trim();
        if (question.isEmpty()) {
            Notification.show("❗ Hãy nhập nội dung cần hỏi Gemini!");
            return;
        }

        aiResponseArea.setValue(aiResponseArea.getValue() + "\n👤 Bạn: " + question + "\n");
        try {
            String aiAnswer = aiService.callGemini(question);
            aiResponseArea.setValue(aiResponseArea.getValue() + "🤖 Gemini: " + aiAnswer + "\n\n");
        } catch (Exception e) {
            aiResponseArea.setValue(aiResponseArea.getValue() + "⚠️ Lỗi khi gọi AI: " + e.getMessage() + "\n");
        }
        aiInputField.clear();
    }
}
