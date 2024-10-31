package ru.example.springsecurity.service;

import ru.example.springsecurity.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User existingUser, User user);

    void deleteUser(User user);

    User getUserByFirstname(String name);

    Optional<User> getUserByEmail(String email);
}
