package ru.yandex.practicum.catsgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

@SpringBootApplication
public class CatsgramApplication {

	public static void main(String[] args) {
			SpringApplication.run(CatsgramApplication.class, args);
	}
}
