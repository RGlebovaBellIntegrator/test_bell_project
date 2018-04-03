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
    EmployeView save(@RequestBody EmployeView employe);

    /**
     * Get all employe
     * @return JSON employe value
     */
    EmployeView employe(@PathVariable Long id);

    List<EmployeView> all();

    /**
     * Get all employe
     * @return JSON employe value
     */

    List<EmployeView>  employe(EmployeView employeView);

    void  update(@RequestBody EmployeView body);

    void  delete(@RequestBody EmployeView body);
}
