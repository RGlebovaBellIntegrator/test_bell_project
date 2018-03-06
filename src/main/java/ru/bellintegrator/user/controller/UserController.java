package ru.bellintegrator.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.user.view.UserView;

import java.util.List;

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
}
