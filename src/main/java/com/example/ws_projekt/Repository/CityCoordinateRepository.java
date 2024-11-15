package com.example.ws_projekt.Repository;

import com.example.ws_projekt.Model.CityCoordinate;
import com.example.ws_projekt.Model.UserModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityCoordinateRepository extends JpaRepository<CityCoordinate, Long> {

    Optional<CityCoordinate> findByCity(String city);
}
