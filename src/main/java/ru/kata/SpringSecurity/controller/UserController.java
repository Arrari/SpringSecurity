package ru.kata.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;
import ru.kata.SpringSecurity.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {this.roleService = roleService;}

    @GetMapping("/authenticated")
    public ModelAndView pageForAuthenticatedUser(Principal principal) {
        ModelAndView mav = new ModelAndView("user-show");
        User user = userService.findByUsername(principal.getName());
        mav.addObject("curUser", user);
        return mav;
    }
}
