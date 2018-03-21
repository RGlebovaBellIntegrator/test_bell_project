package ru.bellintegrator.offices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.offices.view.OfficeView;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

public interface OfficeController {
    /**
     * Add office
     * @param office
     */
    ResponseEntity<?> office(@RequestBody OfficeView office);

    /**
     * Get all offices
     * @return JSON offices value
     */
    ResponseEntity<?> office();

    ResponseEntity<?> list(OfficeView office);

    ResponseEntity<?> update(@RequestBody OfficeView office);

    ResponseEntity<?> delete(@RequestBody OfficeView office);
}
