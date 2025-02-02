package com.example.ws_projekt.Controller.ViewController;

import com.example.ws_projekt.Model.UserModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    @Autowired
    private RestTemplate restTemplate;



    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user, Model model){

    }
}
