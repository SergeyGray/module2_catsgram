package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.PostController;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(Integer size, Integer from, String sort){
        return posts.stream().sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        }).skip(from).limit(size).collect(Collectors.toList());
    }
    public Post findPostById(int id){
        return posts.stream()
                .filter(x -> x.getId() == id)
                .findFirst().get();
    }
    public Post create(Post post){
        if(userService.findUserByMail(post.getAuthor()) != null){
            log.info("Добавлен пост {}", post);
            posts.add(post);
            return post;
        }else{
            throw new UserAlreadyExistException(post.getAuthor());
        }

    }
    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.stream().filter(p -> email.equals(p.getAuthor())).sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        }).limit(size).collect(Collectors.toList());
    }
}
