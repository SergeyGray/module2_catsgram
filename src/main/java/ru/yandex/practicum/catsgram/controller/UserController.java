package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.HashSet;

@RestController
public class UserController {

    private final UserService userService;
    private final HashSet<User> users = new HashSet<User>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public HashSet<User> findAll() {

        return users;
    }
    @GetMapping("/users/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByMail(email);
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
