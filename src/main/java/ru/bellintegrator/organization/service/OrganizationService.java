package ru.bellintegrator.organization.service;

import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    /**
     *
     * @param organization
     */
    OrganizationView add(OrganizationView organization);

    /**
     * Organization service method
     * @return {@Organizations}
     */
    List<OrganizationView> organization();

    List<OrganizationView> list(OrganizationView organizationView);


    void update(OrganizationView organizationView);

    void delete(Long id);

    OrganizationView find(Long id);
}
