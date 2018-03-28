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
     * @return List<Country>
     */
    List<Country> all();

    /**
     * Получить Country по идентификатору
     *
     * @param code
     * @return Country
     */
    Country loadByCode(String code);

    Country loadByName(String name);

    /**
     * Сохранить Country
     *
     * @param country
     */
    void save(Country country);
}
