package ru.bellintegrator.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.CountryDAO;
import ru.bellintegrator.catalog.dao.DocDAO;
import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.offices.dao.OfficeDAO;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.optional.PersistException;
import ru.bellintegrator.user.dao.EmployeDAO;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.view.EmployeView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Repository
public class EmployeDAOImpl implements EmployeDAO {
    private final EntityManager em;
    private final DocDAO doc;
    private final CountryDAO country;
    private final OfficeDAO office;

    @Autowired
    public EmployeDAOImpl(EntityManager em, DocDAO doc, CountryDAO country, OfficeDAO office) {
        this.em = em;
        this.doc = doc;
        this.country = country;
        this.office = office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employe> all() {
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e", Employe.class);
        return query.getResultList();
    }

    @Override
    public List<Employe> filter(EmployeView employeView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

        Root<Employe> employe = criteria.from(Employe.class);

        Predicate predicate = builder.conjunction();
        if (employeView.fullName != null) {
            Predicate p = builder.equal(employe.get("firstname"), employeView.fullName);
            predicate = builder.and(predicate, p);
        }

        if (employeView.secondName != null) {
            Predicate p = builder.equal(employe.get("secondname"), employeView.secondName);
            predicate = builder.and(predicate, p);
        }

        if (employeView.middleName != null) {
            Predicate p = builder.equal(employe.get("middlename"), employeView.middleName);
            predicate = builder.and(predicate, p);
        }

        if (employeView.position != null) {
            Predicate p = builder.equal(employe.get("position"), employeView.position);
            predicate = builder.and(predicate, p);
        }

        if (employeView.docCode != null) {
            Predicate p = builder.equal(employe.get("doc").get("code"), employeView.docCode);
            predicate = builder.and(predicate, p);
        }

        if (employeView.citizenshipCode != null) {
            Predicate p = builder.equal(employe.get("country").get("code"), employeView.citizenshipCode);
            predicate = builder.and(predicate, p);
        }

        if (employeView.officeId != null) {
            Predicate p = builder.equal(employe.get("office").get("id"), employeView.officeId);
            predicate = builder.and(predicate, p);
        } else throw new NoFoundException("Не задан office_id");


        criteria.where(predicate);

        TypedQuery<Employe> query = em.createQuery(criteria);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistException("Ошибка чтения данных", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employe loadById(Long id) {
        return em.find(Employe.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employe loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

        Root<Employe> users = criteria.from(Employe.class);
        criteria.where(builder.equal(users.get("name"), name));

        TypedQuery<Employe> query = em.createQuery(criteria);
        try {
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new NoFoundException("Сотрудник не найден", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long save(Employe employe) {
        try {
            em.persist(employe);
            return employe.getId();
        }
        catch (Exception ex) {
            throw new PersistException("Не удалось сохранить сотрудника", ex);
        }

    }

    @Override
    public void update(EmployeView employeView) {
        if (employeView.id == null)
            throw new NoFoundException("Не задан id");

        Employe employe = loadById(employeView.id);
        if (employe==null)
            throw new NoFoundException("Сотрудник с указанным id не найден");

        if (employeView.firstName != null) {
            employe.setFirstname(employeView.firstName);
        }

        if (employeView.secondName != null) {
            employe.setSecondname(employeView.secondName);
        }

        if (employeView.middleName != null) {
            employe.setMiddlename(employeView.middleName);
        }

        if (employeView.position != null) {
            employe.setStatement(employeView.position);
        }

        if (employeView.phone != null) {
            employe.setPhone(employeView.phone);
        }

        if (employeView.docName != null) {
            Doc doc_temp = doc.loadByName(employeView.docName);
            if (doc_temp != null)
                employe.setDoc(doc_temp);
        }

        if (employeView.docNumber != null) {
            employe.setStatement(employeView.docNumber);
        }

        if (employeView.docDate != null) {
            employe.setDocDate(employeView.docDate);
        }

        if (employeView.citizenshipName != null) {
            Country country_temp = country.loadByName(employeView.citizenshipName);
            if (country_temp != null)
                employe.setCountry(country_temp);
        }

        if (employeView.citizenshipCode != null) {
            Country country_temp = country.loadByCode(employeView.citizenshipCode);
            if (country_temp != null)
                employe.setCountry(country_temp);
        }

        if (employeView.isIdentified != null) {
            employe.setIsIdentified(employeView.isIdentified);
        }

    }


    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NoFoundException("Не задан id");
        Employe employe = loadById(id);
        if (employe!=null){
            em.remove(employe);
        }
        else
            throw new NoFoundException("Нет такого id");
    }

    @Override
    public Doc findDocId(String code) {
        return doc.loadByCode(code);
    }


    @Override
    public Country findCountryId(String code) {
        return country.loadByCode(code);
    }

    @Override
    public Office findOfficeId(Long id) {
        return office.loadById(id);
    }
}
