package com.example.ws_projekt.Controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    public String adminPage(){

        return "AdminPage";
    }

    @DeleteMapping
    public String deleteUsers(){
        return "AdminPage";
    }
}
