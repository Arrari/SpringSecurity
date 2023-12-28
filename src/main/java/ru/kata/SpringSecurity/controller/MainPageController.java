package ru.kata.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;

import java.security.Principal;

@Controller
public class MainPageController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainPageController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    ModelAndView showMainPage(Principal principal) {
        ModelAndView mav = new ModelAndView("main-page");
        mav.addObject("allUsers", userService.allUsers());
        mav.addObject("newUser", new User());
        mav.addObject("allRoles", roleService.getAllRoles());
        mav.addObject("curUser", userService.findByUsername(principal.getName()));
        return mav;
    }
}
