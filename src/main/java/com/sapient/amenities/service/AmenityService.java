package com.sapient.amenities.service;

import com.sapient.amenities.exception.DataNotFoundException;
import com.sapient.amenities.model.Amenity;
import com.sapient.amenities.repository.AmenityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public AmenityService(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    public List<Amenity> getAmenities() {
        return amenityRepository.findAll();
    }

    public Amenity getAmenity(Long amenityId) throws DataNotFoundException {
        return amenityRepository.findById(amenityId)
                .orElseThrow(() -> new DataNotFoundException("Amenity with id : " + amenityId + "was not found"));
    }

    public Long saveAmenity(Amenity amenity) {
        return amenityRepository.save(amenity).getId();
    }

    public void deleteAmenity(Long id) {
        amenityRepository.deleteById(id);
    }
}
