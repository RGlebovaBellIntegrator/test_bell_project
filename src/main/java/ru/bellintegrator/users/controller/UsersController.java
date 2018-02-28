package ru.bellintegrator.users.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.users.view.UsersView;

import java.util.List;

public interface UsersController {
    /**
     * Add users
     * @param users
     */
    void users(@RequestBody UsersView users);

    /**
     * Get all users
     * @return JSON users value
     */
    List<UsersView> users();
}
