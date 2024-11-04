package com.example.ws_projekt.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WebClient weatherWebClientConfig;

    public WeatherController(WebClient.Builder webClient) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.open-meteo.com/v1/forecast")
                .build();
    }



}

