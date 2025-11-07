package iuh.fit.se.trancongminh_week09.controller;


import iuh.fit.se.trancongminh_week09.model.Booking;
import iuh.fit.se.trancongminh_week09.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id) {
        return bookingService.getBookingById(id).orElse(null);
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
