package iuh.fit.se.trancongminh_week09.config;

import iuh.fit.se.trancongminh_week09.model.Booking;
import iuh.fit.se.trancongminh_week09.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Tự động seed dữ liệu chuyến bay mẫu khi ứng dụng khởi động.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private final BookingRepository bookingRepository;

    @PostConstruct
    public void init() {
        try {
            // Xóa dữ liệu cũ
            bookingRepository.deleteAll();
            logger.info("Đã xóa toàn bộ dữ liệu booking cũ.");

            // Tạo các booking mẫu
            // Danh sách booking mẫu
            List<Booking> bookings = List.of(
                    new Booking(null, "Nguyen", "Van A", "Hanoi", "Ho Chi Minh City", "2025-11-15", 2, "Economy"),
                    new Booking(null, "Tran", "Cong Minh", "Ho Chi Minh City", "Tokyo", "2025-12-20", 1, "Business"),
                    new Booking(null, "Le", "Van Tai", "Da Nang", "Singapore", "2025-11-25", 3, "Economy"),
                    new Booking(null, "Pham", "Thi Huong", "Hanoi", "Bangkok", "2025-12-10", 1, "Premium Economy"),
                    new Booking(null, "Luu", "Van Bi", "Ho Chi Minh City", "Seoul", "2025-12-05", 2, "Business"),
                    new Booking(null, "Hoang", "Thi An", "Hanoi", "Phu Quoc", "2025-11-30", 4, "Economy"),
                    new Booking(null, "Do", "Van Khoa", "Da Nang", "Kuala Lumpur", "2025-12-18", 1, "Business"),
                    new Booking(null, "Vo", "Thi Lan", "Hue", "Bangkok", "2025-12-22", 2, "Premium Economy")
            );

            // Lưu vào DB
            bookingRepository.saveAll(bookings);

            logger.info("✅ Đã seed {} bản ghi vào bảng bookings.", bookingRepository.count());
        } catch (Exception e) {
            logger.error("❌ Lỗi khi seed dữ liệu booking: {}", e.getMessage(), e);
        }
    }
}
