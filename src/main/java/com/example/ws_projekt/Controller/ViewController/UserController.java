package com.example.ws_projekt.Controller.ViewController;

import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Model.WeatherModel;
import com.example.ws_projekt.Repository.CityCoordinateRepository;
import com.example.ws_projekt.Repository.UserRepository;
import com.example.ws_projekt.Service.UserService;
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

import java.util.Optional;

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

        restTemplate.postForObject(apiUrl, user, String.class);


        return "UserCreationPage";
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

        }

        model.addAttribute("isLoggedIn", loggedInUser);

        return "UserPage";
    }




}
