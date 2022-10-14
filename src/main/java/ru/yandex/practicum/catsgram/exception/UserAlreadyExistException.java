package ru.yandex.practicum.catsgram.exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException (String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UserAlreadyExistException{"
                + ", message=" + getMessage()
                + "} ";
    }
}
