package ru.kata.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"","/all"})
    public String showUsersList(Model model, Principal principal) {
        List<User> userList = userService.getAllUsers();
        User authUser = userService.findByUsername(principal.getName());
        model.addAttribute("users", userList);
        model.addAttribute("authUser",authUser);
        model.addAttribute("allroles", roleService.getAllRoles());
        return "admin-user-list";
    }

    @GetMapping("/new-user")
    public ModelAndView newUser() {
        User user = new User();
        ModelAndView mav = new ModelAndView("admin-new-user");
        mav.addObject("user", user);
        List<Role> roles = (List<Role>) roleService.getAllRoles();
        mav.addObject("allRoles", roles);
        return mav;
    }

    @PostMapping("/new-user")
    public String addUserToDb(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/all";
    }
    @GetMapping("/show-user/{id}")
    public ModelAndView showUserDetails(@PathVariable long id) {
        User user = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("admin-show-user");
        mav.addObject("user", user);
        List<Role> roles = (List<Role>) roleService.getAllRoles();
        mav.addObject("allRoles", roles);
        return mav;
    }
    @PostMapping("/show-user/admin/admin-update-user/{id}")
    public String updateUser(User user) {
        userService.editUser(user);
        return "redirect:/admin/all";
    }
    @DeleteMapping("show-user/admin-user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin/all";
    }

}
