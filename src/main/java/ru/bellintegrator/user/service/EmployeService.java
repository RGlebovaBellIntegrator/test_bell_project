package ru.bellintegrator.user.service;

import ru.bellintegrator.user.view.EmployeView;

import java.util.List;

public interface EmployeService {

    /**
     *
     * @param employe
     */
    void add(EmployeView employe);

    /**
     * User service method
     * @return {@Employe}
     */
    List<EmployeView> employe();
}
