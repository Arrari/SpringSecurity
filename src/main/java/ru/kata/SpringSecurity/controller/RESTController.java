package ru.kata.SpringSecurity.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User newUser) {
        userService.add(newUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/showMyAccount")
    public ResponseEntity<User> getUserByUsername(Principal principal) {
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userFromWebPage, @PathVariable("id") Long id) {
        userService.edit(userFromWebPage);
        return new ResponseEntity<>(userFromWebPage, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Collection<Role>> getRole(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getById(id).getRoles(), HttpStatus.OK);
    }






}
