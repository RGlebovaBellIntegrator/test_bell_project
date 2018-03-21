package ru.bellintegrator.user.dao;

import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;

import java.util.Date;
import java.util.List;

public interface EmployeDAO {
    /**
     * Получить все объекты Employe
     *
     * @return
     */
    List<Employe> all();

    List<Employe> filter(String firstname, String secondname, String middlename,
                         String position, String doc_code, Long office_id, String country_code);

    /**
     * Получить Employe по идентификатору
     *
     * @param id
     * @return
     */
    Employe loadById(Long id);

    /**
     * Получить Employe по имени
     *
     * @param name
     * @return
     */
    Employe loadByName(String name);

    /**
     * Сохранить Employe
     *
     * @param employe
     */
    void save(Employe employe);

    void update(Long id, String firstname, String secondname, String middlename,
                String position, String phone, String doc_name, String doc_number,
                Date doc_date, String country_name, String country_code, Boolean isIdentified);

    void delete(Long id);

    Doc findDocId(String code);
    Country findCountryId(String code);

    Office findOfficeById(Long id);
}
