package ru.bellintegrator.user.controller;

import io.swagger.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.user.view.EmployeView;

import java.util.List;
import java.util.Map;

public interface EmployeController {
    /**
     * Add employe
     * @param employe
     */
    void employe(@RequestBody EmployeView employe);

    /**
     * Get all employe
     * @return JSON employe value
     */
    List<EmployeView> employe();

    /**
     * Get all employe
     * @return JSON employe value
     */

    List<EmployeView> employe(Map<String,String> body);

    void update(@RequestBody Map<String,String> body);

    void delete(@RequestBody Map<String,String> body);
}
