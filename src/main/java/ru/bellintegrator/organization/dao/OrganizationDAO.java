package ru.bellintegrator.organization.dao;

import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationDAO {
    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> all();

    List<Organization> filter(String name,String inn, Boolean isActive);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     * Получить Organization по имени
     *
     * @param name
     * @return
     */
    Organization loadByName(String name);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    Long save(Organization organization);

    void update(OrganizationView organizationView);

    void delete(Long id);
}
