package ru.kata.SpringSecurity.repository;

import org.springframework.stereotype.Repository;
import ru.kata.SpringSecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
