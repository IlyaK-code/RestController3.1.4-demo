package ru.example.springsecurity.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.example.springsecurity.models.User;
import ru.example.springsecurity.service.RoleService;
import ru.example.springsecurity.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showUsers(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByFirstname(principal.getName()));
        return "demo";
    }
}
