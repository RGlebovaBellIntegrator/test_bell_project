package ru.bellintegrator.organization.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

public interface OrganizationController {
    /**
     * Add organization
     * @param organization
     */
    void save(@RequestBody OrganizationView organization);

    /**
     * Get all organization
     * @return JSON organization value
     */
    List<OrganizationView> organization();

    List<OrganizationView> list(Map<String,String> body);

    void update(@RequestBody Map<String,String> body);

    void delete(@RequestBody Map<String,String> body);
}
