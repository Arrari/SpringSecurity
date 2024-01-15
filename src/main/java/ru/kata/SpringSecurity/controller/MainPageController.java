package ru.kata.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;

import java.security.Principal;


@RequestMapping("/main-page")
@Controller
public class MainPageController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainPageController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    ModelAndView showMainPage(Principal principal) {
        ModelAndView mav = new ModelAndView("main-page");
        User authUser = userService.findByUsername(principal.getName());
        mav.addObject("allUsers", userService.getAllUsers());
        mav.addObject("newUser", new User());
        mav.addObject("allRoles", roleService.getAllRoles());
        mav.addObject("curUser", authUser);
        return mav;
    }
    @PostMapping("/new-user")
    public String addNewUserFromBlank(@ModelAttribute("newUser") User user) {
        userService.add(user);
        return "redirect:/main-page";
    }

    @PatchMapping("/{id}")
    public String saveUpdateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.edit(user);
        return "redirect:/main-page";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/main-page";
    }



}
