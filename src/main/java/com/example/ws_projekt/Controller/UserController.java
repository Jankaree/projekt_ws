package com.example.ws_projekt.Controller;

import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Model.WeatherModel;
import com.example.ws_projekt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final WebClient webClient;

    @Autowired
    public UserController(UserRepository userRepository, WebClient.Builder webClientBuilder) {
        this.userRepository = userRepository;
        this.webClient = webClientBuilder.baseUrl("https://api.open-meteo.com/v1/forecast").build();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {

        List<String> validCities = Arrays.asList("Stockholm", "Seoul", "New-York", "Tokyo");

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.status(400).body("Username cannot be null or empty.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body("Password cannot be null or empty.");
        }
        if (user.getCityOfOrigin() == null || !validCities.contains(user.getCityOfOrigin().getCity())) {
            return ResponseEntity.status(400).body("City of origin must be specified.");
        }

        // Save the new user
        userRepository.save(user);
        return ResponseEntity.status(201).body("User created successfully.");
    }

    @GetMapping("/{id}/weather")
    public Mono<WeatherModel> getWeatherBasedOnUser(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "User with ID " + id + " does not exist.");
        }

        User user = optionalUser.get();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", user.getCityOfOrigin().getLatitude())
                        .queryParam("longitude", user.getCityOfOrigin().getLongitude())
                        .queryParam("hourly", "temperature_2m")
                        .build())
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {

        List<String> validCities = Arrays.asList("Stockholm", "Seoul", "New-York", "Tokyo");

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("User with ID " + id + " does not exist.");
        }

        if (updatedUser.getUsername() == null || updatedUser.getUsername().isEmpty()) {
            return ResponseEntity.status(400).body("Username cannot be null or empty.");
        }
        if (updatedUser.getPassword() == null || updatedUser.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body("Password cannot be null or empty.");
        }

        if (updatedUser.getCityOfOrigin() == null || !validCities.contains(updatedUser.getCityOfOrigin().getCity())) {
            return ResponseEntity.status(400).body("City must be valid.");
        }

        User existingUser = optionalUser.get();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setCityOfOrigin(updatedUser.getCityOfOrigin());
        userRepository.save(existingUser);

        return ResponseEntity.status(200).body("User updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("User with ID " + id + " does not exist.");
        }

        userRepository.deleteById(id);
        return ResponseEntity.status(204).body("User deleted successfully.");
    }

}