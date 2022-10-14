package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;

@Service
public class UserService {

    private final HashSet<User> users = new HashSet<User>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public HashSet<User> findAll(){
        log.info("Количество пользователей {}", users.size());
        return users;
    }
    public User create(User user) {
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Email пользователя не задан");
        }
        if(users.contains(user)){
            throw new UserAlreadyExistException("Данный пользователь уже существует");

        }
        log.info("Добавлен пользователь {}", user);
        users.add(user);
        return user;
    }
    public User update(User user) {
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Email пользователя не задан");
        }
        if(users.contains(user)){
            users.remove(user);
        }
        users.add(user);
        return user;
    }
    public User findUserByMail(String email){
        for(User user:users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
}
