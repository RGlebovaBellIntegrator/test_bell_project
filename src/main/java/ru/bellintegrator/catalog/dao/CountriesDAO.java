package ru.bellintegrator.catalog.dao;

import ru.bellintegrator.catalog.model.Countries;

import java.util.List;

/**
 * DAO для работы с Countries
 */
public interface CountriesDAO {
    /**
     * Получить все объекты Docs
     *
     * @return
     */
    List<Countries> all();

    /**
     * Получить Countries по идентификатору
     *
     * @param code
     * @return
     */
    Countries loadByCode(Integer code);

    /**
     * Сохранить Countries
     *
     * @param countries
     */
    void save(Countries countries);
}
