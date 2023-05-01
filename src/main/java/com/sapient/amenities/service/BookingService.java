package com.sapient.amenities.service;

import com.sapient.amenities.exception.CapacityFullException;
import com.sapient.amenities.exception.DataNotFoundException;
import com.sapient.amenities.model.Amenity;
import com.sapient.amenities.model.Booking;
import com.sapient.amenities.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final AmenityService amenityService;

    public BookingService(BookingRepository bookingRepository, AmenityService amenityService) {
        this.bookingRepository = bookingRepository;
        this.amenityService = amenityService;
    }

    public List<Booking> getBookings(Long userId, LocalDate date) {
        return bookingRepository.findByUserIdAndDate(userId, Date.valueOf(date));
    }

    public Booking getBookingById(Long bookingId) throws DataNotFoundException {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking with id : " + bookingId + "was not found"));
    }

    public Long saveBooking(Booking booking) throws DataNotFoundException, CapacityFullException {
        Amenity amenity = amenityService.getAmenity(booking.getAmenity().getId());
        Long count = bookingRepository.countByAmenity_IdAndDateIsAndStartTimeAndEndTime(amenity.getId(), booking.getDate(), booking.getStartTime(), booking.getEndTime());
        if (count == null) count = 0L;
        if ((amenity.getCapacity() - (count + booking.getCount())) >= 0)
            return bookingRepository.save(booking).getId();

        throw new CapacityFullException("Booking full for amenity : " + amenity.getName());
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
