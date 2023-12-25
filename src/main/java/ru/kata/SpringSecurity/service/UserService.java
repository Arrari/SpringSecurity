package ru.kata.SpringSecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.SpringSecurity.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void addUser(User user);

    void removeUserById(long id);

    void editUser(User user);

    User getUserById(long id);

    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
