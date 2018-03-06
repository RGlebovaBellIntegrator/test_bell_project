package ru.bellintegrator.catalog.dao;

import ru.bellintegrator.catalog.model.Doc;

import java.util.List;

/**
 * DAO для работы с Doc
 */
public interface DocDAO {
    /**
     * Получить все объекты Doc
     *
     * @return
     */
    List<Doc> all();

    /**
     * Получить Doc по идентификатору
     *
     * @param code
     * @return
     */
    Doc loadByCode(Integer code);
    /**
     * Сохранить Doc
     *
     * @param doc
     */
    void save(Doc doc);
}
