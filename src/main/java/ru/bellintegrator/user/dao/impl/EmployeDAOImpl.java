package ru.bellintegrator.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.user.dao.EmployeDAO;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
