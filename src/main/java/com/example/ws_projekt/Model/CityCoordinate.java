package com.example.ws_projekt.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "city_coordinates")
public class CityCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private double latitude;
    private double longitude;

    public CityCoordinate() {}

    public CityCoordinate(String city, double latitude, double longitude) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
