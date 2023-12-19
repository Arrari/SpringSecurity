package ru.kata.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getByName(String rolename) {
        return roleRepository.findRoleByRolename(rolename);
    }

    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

}
