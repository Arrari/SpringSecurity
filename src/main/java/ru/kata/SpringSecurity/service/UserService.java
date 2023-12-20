package ru.kata.SpringSecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.SpringSecurity.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> allUsers();

    void add(User user);

    void remove(long id);

    void edit(User user);

    User getById(long id);

    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
