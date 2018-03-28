package ru.bellintegrator.organization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

public interface OrganizationController {
    /**
     * Add organization
     * @param organization
     */
    Data save(@RequestBody OrganizationView organization);

    /**
     * Get all organization
     * @return JSON organization value
     */
    Data organization(@RequestBody Long id);

    Data list(OrganizationView body);

    Data all();

    Data update(@RequestBody OrganizationView body);

    Data delete(@RequestBody OrganizationView body);

}
