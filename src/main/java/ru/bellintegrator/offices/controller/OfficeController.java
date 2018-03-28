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
    Data office(@RequestBody OfficeView office);

    /**
     * Get all offices
     * @return JSON offices value
     */
    Data office(@PathVariable Long id);
    Data all();

    Data list(OfficeView office);

    Data update(@RequestBody OfficeView office);

    Data delete(@RequestBody OfficeView office);
}
