package com.example.ws_projekt.Controller;

import com.example.ws_projekt.Model.CityCoordinate;
import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityCoordinateRepository cityCoordinateRepository;

    @Autowired
    public CityController(CityCoordinateRepository cityCoordinateRepository) {
        this.cityCoordinateRepository = cityCoordinateRepository;
    }

    @PostMapping
    public ResponseEntity<String> addCity(@RequestBody CityCoordinate cityCoordinate){
        if (cityCoordinate.getCity() == null || cityCoordinate.getCity().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City name cannot be null or empty.");
        }

        cityCoordinateRepository.save(cityCoordinate);

        return ResponseEntity.status(HttpStatus.CREATED).body("City created successfully.");
    }
}