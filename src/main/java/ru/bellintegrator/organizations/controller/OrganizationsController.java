package ru.bellintegrator.organizations.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.organizations.view.OrganizationView;

import java.util.List;

public interface OrganizationsController {
    /**
     * Add organizations
     * @param organizations
     */
    void organizations(@RequestBody OrganizationView organizations);

    /**
     * Get all organizations
     * @return JSON organizations value
     */
    List<OrganizationView> organizations();
}
