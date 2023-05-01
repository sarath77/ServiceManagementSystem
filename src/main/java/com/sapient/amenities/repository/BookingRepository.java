package com.sapient.amenities.repository;

import com.sapient.amenities.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByUserIdAndDate(Long userId, Date date);

    @Query("select sum(b.count) from Booking b where b.amenity.id = ?1 and b.date = ?2 and b.startTime = ?3 and b.endTime = ?4")
    Long countByAmenity_IdAndDateIsAndStartTimeAndEndTime(long amenity_id, Date date, Time startTime, Time endTime);
}
