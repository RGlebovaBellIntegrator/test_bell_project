package ru.bellintegrator.offices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.offices.view.OfficeView;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

public interface OfficeController {
    /**
     * Add office
     * @param office
     */
    OfficeView office(@RequestBody OfficeView office);

    /**
     * Get all offices
     * @return JSON offices value
     */
    OfficeView office(@PathVariable Long id);
    List<OfficeView> all();

    List<OfficeView> list(OfficeView office);

    void update(@RequestBody OfficeView office);

    void delete(@RequestBody OfficeView office);
}
