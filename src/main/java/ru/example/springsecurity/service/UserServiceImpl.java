package ru.example.springsecurity.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.springsecurity.models.Role;
import ru.example.springsecurity.models.User;
import ru.example.springsecurity.repository.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public User saveUser(User existingUser, User user) {
        if (existingUser != null && (user.getPassword() == null || user.getPassword().isEmpty())) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (existingUser != null) {
            user.setId(existingUser.getId());
        }
        if (existingUser != null && (user.getAuthorities() == null || user.getAuthorities().isEmpty())) {
            user.setRoles((Set<Role>) existingUser.getAuthorities());
        }
        return userRepo.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    @Override
    public User getUserByFirstname(String name) {
        return userRepo.findByFirstname(name);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
