package ru.bellintegrator.offices.dao;

import ru.bellintegrator.offices.model.Office;

import java.util.List;

public interface OfficeDAO {
    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Получить Office по имени
     *
     * @param name
     * @return
     */
    Office loadByName(String name);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);
}
