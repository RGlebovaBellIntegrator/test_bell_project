package ru.bellintegrator.offices.dao;

import ru.bellintegrator.offices.model.Offices;

import java.util.List;

public interface OfficesDAO {
    /**
     * Получить все объекты Offices
     *
     * @return
     */
    List<Offices> all();

    /**
     * Получить Offices по идентификатору
     *
     * @param id
     * @return
     */
    Offices loadById(Long id);

    /**
     * Получить Offices по имени
     *
     * @param name
     * @return
     */
    Offices loadByName(String name);

    /**
     * Сохранить Offices
     *
     * @param offices
     */
    void save(Offices offices);
}
