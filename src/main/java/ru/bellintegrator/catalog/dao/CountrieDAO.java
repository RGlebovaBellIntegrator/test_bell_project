package ru.bellintegrator.catalog.dao;

import ru.bellintegrator.catalog.model.Countrie;

import java.util.List;

/**
 * DAO для работы с Countrie
 */
public interface CountrieDAO {
    /**
     * Получить все объекты Doc
     *
     * @return
     */
    List<Countrie> all();

    /**
     * Получить Countrie по идентификатору
     *
     * @param code
     * @return
     */
    Countrie loadByCode(Integer code);

    /**
     * Сохранить Countrie
     *
     * @param countrie
     */
    void save(Countrie countrie);
}
