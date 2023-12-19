package ru.kata.SpringSecurity.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.models.User;
import ru.kata.SpringSecurity.repository.RoleRepository;
import ru.kata.SpringSecurity.service.RoleService;
import ru.kata.SpringSecurity.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public Init(UserService us, RoleRepository rrep) {
        this.userService = us;
        this.roleRepository = rrep;
    }

    @Transactional
    @PostConstruct
    public void starterDataBase() {
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));
        Set<Role> usersRoles = new HashSet<>();
        Set<Role> adminRoles = new HashSet<>();
        usersRoles.add(roleRepository.findRoleByRoleName("ROLE_USER"));
        adminRoles.add(roleRepository.findRoleByRoleName("ROLE_ADMIN"));
        User admin = new User(
                "Test_Admin",
                "Test_Adminsn",
                "mail@mail.com",
                "admin",
                "admin",
                adminRoles);
        User user = new User(
                "Test_user",
                "Test_user_sn",
                "testmail@mail.com",
                "user",
                "user",
                usersRoles);
        userService.saveUser(admin);
        userService.saveUser(user);

    }
}
