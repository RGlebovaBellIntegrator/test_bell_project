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

    void update(Long id, String firstname, String secondname, String middlename,
                String position, String phone, String doc_name, String doc_number,
                String doc_date, String country_name, String country_code, Boolean isIdentified);

    void delete(Long id);
}
