package ru.bellintegrator.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.user.view.EmployeView;

import java.util.List;
import java.util.Map;

public interface EmployeController {
    /**
     * Add employe
     * @param employe
     */
    ResponseEntity<?> save(@RequestBody EmployeView employe);

    /**
     * Get all employe
     * @return JSON employe value
     */
    //List<EmployeView> employe();
    ResponseEntity<?> employe(@PathVariable Long id);

    ResponseEntity<?> all();

    /**
     * Get all employe
     * @return JSON employe value
     */

    ResponseEntity<?>  employe(EmployeView employeView);

    ResponseEntity<?>  update(@RequestBody EmployeView body);

    ResponseEntity<?>  delete(@RequestBody EmployeView body);
}
