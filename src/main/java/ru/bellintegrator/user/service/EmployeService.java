package ru.bellintegrator.user.service;

import ru.bellintegrator.user.view.EmployeView;

import java.util.Date;
import java.util.List;

public interface EmployeService {

    /**
     *
     * @param employe
     */
    EmployeView add(EmployeView employe);

    /**
     * User service method
     * @return {@Employe}
     */
    List<EmployeView> employe();

    List<EmployeView> employeFilter(EmployeView employeView);

    void update(EmployeView employeView);

    void delete(Long id);

    EmployeView find(Long id);
}
