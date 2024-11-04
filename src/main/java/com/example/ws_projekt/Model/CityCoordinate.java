package com.example.ws_projekt.Model;

public class CityCoordinate {

    private final String city;
    private final double latitude;
    private final double longitude;

    public CityCoordinate(String city, double latitude, double longitude) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
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
