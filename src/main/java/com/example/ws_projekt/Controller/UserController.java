package com.example.ws_projekt.Controller;


import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Optional;



@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository, WebClient.Builder webClientBuilder){
        this.userRepository = userRepository;

    }


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username cannot be null or empty.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null or empty.");
        }

        // Save the new user
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }




}
