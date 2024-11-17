package com.example.ws_projekt.Model.DailyModel;

public class DailyUnits {
    private String temperature_2m_max;
    private String temperature_2m_min;
    private String precipitation_sum;

    public String getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(String temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public String getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(String temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public String getPrecipitation_sum() {
        return precipitation_sum;
    }

    public void setPrecipitation_sum(String precipitation_sum) {
        this.precipitation_sum = precipitation_sum;
    }
}
