package ru.kata.SpringSecurity.repository;

import org.springframework.stereotype.Repository;
import ru.kata.SpringSecurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRoleName(String roleName);
}
