package ru.bellintegrator.user.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.user.view.UserView;

import java.util.List;
import java.util.Map;

public interface UserController {
    /**
     * Add user
     * @param users
     */
    void user(@RequestBody UserView users);

    /**
     * Get all user
     * @return JSON user value
     */
    List<UserView> user();

    //boolean login(@RequestBody String login, String password);

    boolean login(@RequestBody Map<String,String> body);
}
