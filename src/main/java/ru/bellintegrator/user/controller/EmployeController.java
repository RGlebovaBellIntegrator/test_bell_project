package ru.bellintegrator.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.user.view.EmployeListView;
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
    ResponseEntity<?> employe();

    /**
     * Get all employe
     * @return JSON employe value
     */

    List<EmployeListView> employe(Map<String,String> body);

    void update(@RequestBody Map<String,String> body);

    void delete(@RequestBody Map<String,String> body);
}
