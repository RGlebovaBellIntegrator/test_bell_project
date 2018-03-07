package ru.bellintegrator.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.user.view.EmployeView;
import ru.bellintegrator.user.view.UserView;

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
    List<EmployeView> employe(Map<String,String> body);
}
