package ru.bellintegrator.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.user.dao.EmployeDAO;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;
import ru.bellintegrator.user.view.EmployeView;
import ru.bellintegrator.user.view.UserView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.function.Function;

@Repository
public class EmployeDAOImpl implements EmployeDAO {
    private final EntityManager em;

    @Autowired
    public EmployeDAOImpl(EntityManager em) {
        this.em = em;
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
    public List<Employe> filter(String firstname, String secondname, String middlename,
                                String position, String doc_code, String office_id, String country_code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

        Root<Employe> employe = criteria.from(Employe.class);

        Predicate predicate = builder.conjunction();
        if (firstname != null) {
            Predicate p = builder.equal(employe.get("firstname"), firstname);
            predicate = builder.and(predicate, p);
        }

        if (secondname != null) {
            Predicate p = builder.equal(employe.get("secondname"), secondname);
            predicate = builder.and(predicate, p);
        }

        if (middlename != null) {
            Predicate p = builder.equal(employe.get("middlename"), middlename);
            predicate = builder.and(predicate, p);
        }

        if (position != null) {
            Predicate p = builder.equal(employe.get("statement"), position);
            predicate = builder.and(predicate, p);
        }

        if (doc_code != null) {
            Predicate p = builder.equal(employe.get("doc").get("code"), doc_code);
            predicate = builder.and(predicate, p);
        }

        if (country_code != null) {
            Predicate p = builder.equal(employe.get("country").get("code"), country_code);
            predicate = builder.and(predicate, p);
        }

        if (office_id != null) {
            Predicate p = builder.equal(employe.get("office").get("id"), office_id);
            predicate = builder.and(predicate, p);
        }
        else throw new NullPointerException("office_id не инициализирован");;

        criteria.where(predicate);

        TypedQuery<Employe> query = em.createQuery(criteria);
        return query.getResultList();
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
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Employe employe) {
        em.persist(employe);
    }
}
