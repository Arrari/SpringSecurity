package ru.kata.SpringSecurity.service;

import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.repository.RoleRepository;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);

    Role getByName(String rolename);

    Role getRoleById(Long id) ;
}
