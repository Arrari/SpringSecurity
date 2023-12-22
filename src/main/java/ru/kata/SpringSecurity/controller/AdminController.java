package ru.kata.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<User> userList = userService.allUsers();
        User authUser = userService.findByUsername(principal.getName());
        model.addAttribute("users", userList);
        model.addAttribute("authUser",authUser);
        model.addAttribute("allroles", roleService.getAllRoles());
        return "admin-users-list";
    }

    @GetMapping("/new-user")
    public String createBlankNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allroles", roleService.getAllRoles());
        return "admin-new-user";
    }

    @PostMapping("/new-user")
    public String addUserToDb(User user, @RequestParam("selectedRoles") Long[] selectedRoles) {
        Set<Role> rolesById = Arrays.stream(selectedRoles)
                .map(s -> roleService.getRoleById(s))
                .collect(Collectors.toSet());;
        user.setRoles(rolesById);
        userService.add(user);
        return "redirect:admin/";
    }
}
