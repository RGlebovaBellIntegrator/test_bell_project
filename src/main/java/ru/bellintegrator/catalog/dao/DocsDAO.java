package ru.bellintegrator.catalog.dao;

import ru.bellintegrator.catalog.model.Docs;

import java.util.List;

/**
 * DAO для работы с Docs
 */
public interface DocsDAO {
    /**
     * Получить все объекты Docs
     *
     * @return
     */
    List<Docs> all();

    /**
     * Получить Docs по идентификатору
     *
     * @param code
     * @return
     */
    Docs loadByCode(Integer code);
}
