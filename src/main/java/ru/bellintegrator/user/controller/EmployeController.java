package ru.bellintegrator.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.optional.Data;
import ru.bellintegrator.user.view.EmployeView;

import java.util.List;
import java.util.Map;

public interface EmployeController {
    /**
     * Add employe
     * @param employe
     */
    Data save(@RequestBody EmployeView employe);

    /**
     * Get all employe
     * @return JSON employe value
     */
    Data employe(@PathVariable Long id);

    Data all();

    /**
     * Get all employe
     * @return JSON employe value
     */

    Data  employe(EmployeView employeView);

    Data  update(@RequestBody EmployeView body);

    Data  delete(@RequestBody EmployeView body);
}
