package ru.bellintegrator.user.dao;

import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;
import ru.bellintegrator.user.view.EmployeView;

import java.util.Date;
import java.util.List;

public interface EmployeDAO {
    /**
     * Получить все объекты Employe
     *
     * @return
     */
    List<Employe> all();

    List<Employe> filter(EmployeView employeView);

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
    Long save(Employe employe);

    void update(EmployeView employeView);

    void delete(Long id);

    Doc findDocId(String code);
    Country findCountryId(String code);
    Office findOfficeId(Long id);
}
