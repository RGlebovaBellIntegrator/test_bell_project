package ru.bellintegrator.organization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

public interface OrganizationController {
    /**
     * Add organization
     * @param organization
     */
    ResponseEntity<?> save(@RequestBody OrganizationView organization);

    /**
     * Get all organization
     * @return JSON organization value
     */
    ResponseEntity<?> organization(@RequestBody Long id);

    ResponseEntity<?> list(OrganizationView body);

    ResponseEntity<?> all();

    ResponseEntity<?> update(@RequestBody OrganizationView body);

    ResponseEntity<?> delete(@RequestBody OrganizationView body);

}
