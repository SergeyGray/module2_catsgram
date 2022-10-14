package ru.yandex.practicum.catsgram.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("Пользователь " + email + " не найден");
    }

    @Override
    public String toString() {
        return "UserNotFoundException{"
                + ", message=" + getMessage()
                + "} ";
    }
}
