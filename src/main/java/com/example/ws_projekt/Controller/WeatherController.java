package com.example.ws_projekt.Controller;


import com.example.ws_projekt.Model.CityCoordinate;
import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Model.WeatherModel;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
import com.example.ws_projekt.Repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WebClient weatherWebClientConfig;
    private final UserRepository userRepository;

    private final CityCoordinateRepository cityCoordinateRepository;

    public WeatherController(WebClient.Builder webClient, UserRepository userRepository, CityCoordinateRepository cityCoordinateRepository) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.open-meteo.com/v1/forecast")
                .build();
        this.userRepository = userRepository;
        this.cityCoordinateRepository = cityCoordinateRepository;
    }


    @GetMapping("/{city}")
    public Mono<WeatherModel> getWeatherAtCity(@PathVariable String city) {


        Optional<CityCoordinate> cityName = cityCoordinateRepository.findByCity(city);

        if (cityName.isEmpty()){
            return Mono.error(new RuntimeException("City not found"));
        }

        CityCoordinate foundCity = cityName.get();

                return weatherWebClientConfig.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("latitude", foundCity.getLatitude())
                                .queryParam("longitude", foundCity.getLongitude())
                                .queryParam("hourly", "temperature_2m")
                                .build())
                        .retrieve()
                        .bodyToMono(WeatherModel.class);




        //return Mono.error(new RuntimeException("City not found"));

        }


    @GetMapping("/{id}/weekly")
    public Mono<WeatherModel> getWeatherWeekly(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return Mono.error(new RuntimeException("User not found"));
        }

        User user = optionalUser.get();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);

        return weatherWebClientConfig.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", user.getCityOfOrigin().getLatitude())
                        .queryParam("longitude", user.getCityOfOrigin().getLongitude())
                        .queryParam("hourly", "temperature_2m")
                        .queryParam("start_date", startDate.toString())
                        .queryParam("end_date", endDate.toString())
                        .build())
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }

    @GetMapping("/{id}/daily/{date}")
    public Mono<WeatherModel> getDailyweatherForDate(@PathVariable Long id, @PathVariable String date){
        LocalDate localDate = LocalDate.parse(date);

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return Mono.error(new RuntimeException("User not found"));
        }

        User user = optionalUser.get();

        return weatherWebClientConfig.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", user.getCityOfOrigin().getLatitude())
                        .queryParam("longitude", user.getCityOfOrigin().getLongitude())
                        .queryParam("daily", "temperature_2m_max,temperature_2m_min")
                        .queryParam("start_date", date)
                        .queryParam("end_date", date)
                        .build())
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }



    }






