package com.jrs.controller;

import com.jrs.model.User;
import com.jrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody Map<String, String> body) {
        String type = body.get("type");
        String name = body.get("name");
        String email = body.get("email");
        return userService.registerUser(type, name, email);
    }
}