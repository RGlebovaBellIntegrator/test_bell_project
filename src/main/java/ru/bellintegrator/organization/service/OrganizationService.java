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
}
