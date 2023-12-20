package ru.kata.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "page for logged user: " + user.getUsername();
    }
}
