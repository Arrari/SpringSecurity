package ru.kata.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.SpringSecurity.models.Role;
import ru.kata.SpringSecurity.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {roleRepository.save(role);}

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getByName(String rolename) {
        return roleRepository.findRoleByRolename(rolename);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

}
