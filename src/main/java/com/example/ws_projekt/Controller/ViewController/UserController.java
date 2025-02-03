package com.example.ws_projekt.Controller.ViewController;

import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {


    private final RestTemplate restTemplate;
    private final CityCoordinateRepository cityCoordinateRepository;

    @Autowired
    public UserController(RestTemplate restTemplate, CityCoordinateRepository cityCoordinateRepository) {
        this.restTemplate = restTemplate;
        this.cityCoordinateRepository = cityCoordinateRepository;
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

        restTemplate.postForObject(apiUrl, user, String.class);


        return "UserCreationPage";
    }


}
