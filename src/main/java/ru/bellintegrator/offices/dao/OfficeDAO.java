package ru.bellintegrator.offices.dao;

import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.organization.model.Organization;

import java.util.List;

public interface OfficeDAO {
    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Получить Office по имени
     *
     * @param name
     * @return
     */
    Office loadByName(String name);

    List<Office> filter(Long orgId, String name,String phone, Boolean isActive);

    /**
     * Сохранить Office
     *
     * @param office
     */
    Long save(Office office);

    void update(Long id, String name, String address, String phone, Boolean isActive);

    void delete(Long id);

    Organization findOrgById(Long id);
}
