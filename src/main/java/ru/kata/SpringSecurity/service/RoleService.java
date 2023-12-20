package ru.kata.SpringSecurity.service;

import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.repository.RoleRepository;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getByName(String rolename);

    Role getRoleById(Long id) ;
}
