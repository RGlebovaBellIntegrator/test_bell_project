package ru.bellintegrator.organization.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationController {
    /**
     * Add organization
     * @param organization
     */
    void organization(@RequestBody OrganizationView organization);

    /**
     * Get all organization
     * @return JSON organization value
     */
    List<OrganizationView> organization();
}
