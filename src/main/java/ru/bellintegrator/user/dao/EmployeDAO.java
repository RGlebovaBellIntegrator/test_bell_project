package ru.bellintegrator.user.dao;

import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;

import java.util.List;

public interface EmployeDAO {
    /**
     * Получить все объекты Employe
     *
     * @return
     */
    List<Employe> all();

    List<Employe> filter(String firstname, String secondname, String middlename,
                         String position, String doc_code, String office_id, String country_code);

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
}
