package ru.bellintegrator.offices.controller;

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
    void office(@RequestBody OfficeView office);

    /**
     * Get all offices
     * @return JSON offices value
     */
    List<OfficeView> office();

    List<OfficeView> list(Map<String,String> body);

    void update(@RequestBody Map<String,String> body);

    void delete(@RequestBody Map<String,String> body);
}
