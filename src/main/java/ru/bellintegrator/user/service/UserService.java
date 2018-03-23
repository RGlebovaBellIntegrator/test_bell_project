package ru.bellintegrator.user.service;

import ru.bellintegrator.user.view.UserView;

import java.util.List;

public interface UserService {

    /**
     *
     * @param user
     */
    void add(UserView user);

    /**
     * User service method
     * @return {@User}
     */
    List<UserView> user();

    void login(UserView userView);

    Boolean activation(String code);
}
