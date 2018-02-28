package ru.bellintegrator.organizations.dao;

import ru.bellintegrator.organizations.model.Organizations;

import java.util.List;

public interface OrganizationsDAO {
    /**
     * Получить все объекты Organizations
     *
     * @return
     */
    List<Organizations> all();

    /**
     * Получить Organizations по идентификатору
     *
     * @param id
     * @return
     */
    Organizations loadById(Long id);

    /**
     * Получить Organizations по имени
     *
     * @param name
     * @return
     */
    Organizations loadByName(String name);

    /**
     * Сохранить Organizations
     *
     * @param organizations
     */
    void save(Organizations organizations);
}
