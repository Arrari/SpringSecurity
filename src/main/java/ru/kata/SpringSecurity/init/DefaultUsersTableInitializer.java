package ru.kata.SpringSecurity.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultUsersTableInitializer {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DefaultUsersTableInitializer(UserService us, RoleService rs) {
        this.userService = us;
        this.roleService = rs;
    }

    @PostConstruct
    public void addTestUsers() {
        Role userTestRole = new Role("ROLE_USER");
        Role adminTestRole = new Role("ROLE_ADMIN");
        roleService.addRole(userTestRole);
        roleService.addRole(adminTestRole);

        Set<Role> userRoleSet = Stream.of(userTestRole).collect(Collectors.toSet());
        Set<Role> adminRoleSet = Stream.of(adminTestRole).collect(Collectors.toSet());

        User userTestUser = new User(
                "userfn",
                "userln",
                "email@domain.com",
                "user",
                "user",
                userRoleSet);
        User adminTestUser = new User(
                "adminfn",
                "adminsn",
                "admin@domain.com",
                "admin",
                "admin",
                adminRoleSet
        );
        userService.add(userTestUser);
        userService.add(adminTestUser);
    }
}


