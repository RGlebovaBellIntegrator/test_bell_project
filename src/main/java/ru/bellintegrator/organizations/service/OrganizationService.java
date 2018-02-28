package ru.bellintegrator.organizations.service;

import ru.bellintegrator.organizations.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    /**
     *
     * @param organizations
     */
    void add(OrganizationView organizations);

    /**
     * Organization service method
     * @return {@Organizations}
     */
    List<OrganizationView> organizations();
}
