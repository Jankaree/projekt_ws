package com.example.ws_projekt.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class CityCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private double latitude;
    private double longitude;

    public CityCoordinate() {}

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
