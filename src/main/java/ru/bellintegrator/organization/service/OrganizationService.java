package ru.bellintegrator.organization.service;

import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    /**
     *
     * @param organization
     */
    void add(OrganizationView organization);

    /**
     * Organization service method
     * @return {@Organizations}
     */
    List<OrganizationView> organization();

    List<OrganizationView> list(String name,String inn, Boolean isActive);


    void update(Long id, String name, String fullName, String inn,
                String kpp, String address, String phone, Boolean isActive);

    void delete(Long id);
}
