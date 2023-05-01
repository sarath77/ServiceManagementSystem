package com.sapient.amenities.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
public class Booking {
    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    Amenity amenity;

    Long userId;
    int count;
    Date date;
    Time startTime;
    Time endTime;

    public void setAmenity(Long amenityId) {
        Amenity amenity = new Amenity();
        amenity.setId(amenityId);
        this.amenity = amenity;
    }
}
