package com.jrs.service;

import com.jrs.model.User;
import com.jrs.patterns.UserFactory;
import com.jrs.patterns.LoggerSingleton;
import com.jrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String type, String name, String email) {
        LoggerSingleton.getInstance().log("Registering new " + type);

        User newUser = UserFactory.createUser(type, name, email);

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}