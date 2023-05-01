package com.sapient.amenities.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
public class Amenity {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    String type;
    int capacity;

}
