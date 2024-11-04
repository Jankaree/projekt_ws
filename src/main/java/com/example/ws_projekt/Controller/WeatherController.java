package com.example.ws_projekt.Controller;


import com.example.ws_projekt.Model.CityCoordinate;
import com.example.ws_projekt.Model.WeatherModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WebClient weatherWebClientConfig;

    public WeatherController(WebClient.Builder webClient) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.open-meteo.com/v1/forecast")
                .build();
    }

    private static final List<CityCoordinate> cities = List.of(
            new CityCoordinate("Stockholm", 59.33, 18.06),
            new CityCoordinate("Seoul", 37.53, 127.02),
            new CityCoordinate("New-York", 40.73, -73.935),
            new CityCoordinate("Tokyo", 35.65, 139.839)
    );

    @GetMapping("/{city}")
    public Mono<WeatherModel> getWeatherAtCity(@PathVariable String city) {


        for (CityCoordinate coordinate : cities) {
            if (coordinate.getCity().equalsIgnoreCase(city)) {
                return weatherWebClientConfig.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("latitude", coordinate.getLatitude())
                                .queryParam("longitude", coordinate.getLongitude())
                                .queryParam("hourly", "temperature_2m")
                                .build())
                        .retrieve()
                        .bodyToMono(WeatherModel.class);

            }
        }

        return Mono.error(new RuntimeException("City not found"));

        }

    }






