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

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usersId", userService.getUserById(id));
        return "/demo";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "demo";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user) {
        userService.saveUser(null, user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String editUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("roles", );
//        model.addAttribute("user", userService.findUserById(id));
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid User user) {
        userService.saveUser(userService.getUserById(id), user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin";
    }
}
