package ru.bellintegrator.users.dao;

import ru.bellintegrator.users.model.Users;

import java.util.List;

public interface UsersDAO {
    /**
     * Получить все объекты Users
     *
     * @return
     */
    List<Users> all();

    /**
     * Получить Users по идентификатору
     *
     * @param id
     * @return
     */
    Users loadById(Long id);

    /**
     * Получить Users по имени
     *
     * @param name
     * @return
     */
    Users loadByName(String name);

    /**
     * Сохранить Users
     *
     * @param users
     */
    void save(Users users);
}
