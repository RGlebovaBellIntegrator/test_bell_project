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

    List<OrganizationView> list(OrganizationView organizationView);


    void update(OrganizationView organizationView);

    void delete(Long id);
}
