package com.sapient.amenities.controller;

import com.sapient.amenities.model.Amenity;
import com.sapient.amenities.service.AmenityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenity")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping()
    public List<Amenity> getAmenities() {
        return amenityService.getAmenities();
    }

    @PostMapping()
    public Long addAmenity(@RequestBody Amenity amenity) {
        return amenityService.saveAmenity(amenity);
    }

    @DeleteMapping("/{id}")
    public void deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
    }
}
