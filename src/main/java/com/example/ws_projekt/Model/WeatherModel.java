package com.example.ws_projekt.Model;

public class WeatherModel {
    private  double latitude;
    private  double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private int elevation;
    private HourlyUnits hourly_Units;
    private Hourly hourly;
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getGenerationtime_ms() {
        return generationtime_ms;
    }
    public void setGenerationtime_ms(double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }
    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }
    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }
    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }
    public int getElevation() {
        return elevation;
    }
    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
    public HourlyUnits getHourly_Units() {
        return hourly_Units;
    }
    public void setHourly_Units(HourlyUnits hourly_Units) {
        this.hourly_Units = hourly_Units;
    }
    public Hourly getHourly() {
        return hourly;
    }
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

}