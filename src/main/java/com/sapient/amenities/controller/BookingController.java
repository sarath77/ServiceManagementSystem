package com.sapient.amenities.controller;

import com.sapient.amenities.exception.CapacityFullException;
import com.sapient.amenities.exception.DataNotFoundException;
import com.sapient.amenities.model.Booking;
import com.sapient.amenities.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking/{id}")
    public Booking getBookingWithId(@PathVariable Long id) throws DataNotFoundException {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/booking")
    public Long bookAmenity(@RequestBody Booking booking) throws DataNotFoundException, CapacityFullException {
        return bookingService.saveBooking(booking);
    }

    @GetMapping("/bookings/{userId}")
    public List<Booking> getBookings(@PathVariable Long userId, @RequestParam(required = false) LocalDate date) {
        if (date == null) date = LocalDate.now();
        return bookingService.getBookings(userId, date);
    }

    @DeleteMapping("/booking/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }
}
