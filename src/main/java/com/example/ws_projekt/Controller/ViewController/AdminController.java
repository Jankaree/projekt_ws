package com.example.ws_projekt.Controller.ViewController;

import com.example.ws_projekt.Model.UserModel.User;
import com.example.ws_projekt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(Model model){

        List<User> listOfUsers = userService.getAllUsers();

        model.addAttribute("Users",listOfUsers);

        return "AdminPage";
    }

    @DeleteMapping
    public String deleteUsers(){
        return "AdminPage";
    }
}
