package com.example.ws_projekt.Controller.ViewController;

import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Model.WeatherModel;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
import com.example.ws_projekt.Repository.UserRepository;
import com.example.ws_projekt.Service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

@Controller
public class UserController {


    private final RestTemplate restTemplate;
    private final CityCoordinateRepository cityCoordinateRepository;

    private final UserRepository userRepository;

    private final UserService userService;


    @Autowired
    public UserController(RestTemplate restTemplate, CityCoordinateRepository cityCoordinateRepository, UserRepository userRepository, UserService userService) {
        this.restTemplate = restTemplate;
        this.cityCoordinateRepository = cityCoordinateRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String userCreation(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("cities", cityCoordinateRepository.findAll());
        return "UserCreationPage";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user, Model model) {

        String apiUrl = "http://localhost:8080/api/user";
        Boolean messageBoolean = true;

        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            model.addAttribute("messageBoolean", messageBoolean);
            return "UserCreationPage";
        }else {

            restTemplate.postForObject(apiUrl, user, String.class);
            return "UserCreationPage";
        }

    }

    @GetMapping("/user/userPage")
    public String userPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        boolean loggedInUser = userService.isLoggedIn();

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            model.addAttribute("user", optionalUser.get());

            Map<String, Object> weatherData = restTemplate.getForObject("http://localhost:8080/api/weather/" + user.getId() + "/weekly", Map.class);
            Map<String, Object> hourlyDataMap = (Map<String, Object>) weatherData.get("hourly");

            List<String> times = (List<String>) hourlyDataMap.get("time");
            List<Double> temperatures = (List<Double>) hourlyDataMap.get("temperature_2m");

            List<Map<String, Object>> weatherAt14 = new ArrayList<>();

            for (int i = 0; i < times.size(); i++) {
                if (times.get(i).endsWith("14:00")) {
                    Map<String, Object> weatherAt14Data = new HashMap<>();
                    weatherAt14Data.put("time", times.get(i));
                    weatherAt14Data.put("temperature", temperatures.get(i));
                    weatherAt14.add(weatherAt14Data);
                }
            }

            model.addAttribute("weatherAt14", weatherAt14);

        }

        model.addAttribute("isLoggedIn", loggedInUser);

        return "UserPage";
    }




}







