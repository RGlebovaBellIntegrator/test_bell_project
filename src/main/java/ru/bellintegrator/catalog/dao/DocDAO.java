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
     * @return List<Doc>
     */
    List<Doc> all();

    /**
     * Получить Doc по идентификатору
     *
     * @param code
     * @return Doc
     */
    Doc loadByCode(String code);

    Doc loadByName(String name);
    /**
     * Сохранить Doc
     *
     * @param doc
     */
    void save(Doc doc);
}
