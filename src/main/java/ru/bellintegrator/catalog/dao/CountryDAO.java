package ru.bellintegrator.catalog.dao;

import ru.bellintegrator.catalog.model.Country;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDAO {
    /**
     * Получить все объекты Doc
     *
     * @return
     */
    List<Country> all();

    /**
     * Получить Country по идентификатору
     *
     * @param code
     * @return
     */
    Country loadByCode(Integer code);

    /**
     * Сохранить Country
     *
     * @param country
     */
    void save(Country country);
}
