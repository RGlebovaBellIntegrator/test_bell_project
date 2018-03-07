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

    List<EmployeView> employeFilter(String firstname, String secondname, String middlename,
                                    String position, String doc_code, String office_id, String country_code);
}
