package ru.bellintegrator.user.dao;

import ru.bellintegrator.user.model.User;

import java.util.List;

public interface UserDAO {
    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> all();

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    /**
     * Получить User по имени
     *
     * @param name
     * @return
     */
    User loadByName(String name);

    /**
     * Получить User по логину
     *
     * @param login
     * @return
     */
    User loadByLogin(String login, String password);

    User loadByCode(String code);

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);
}
