package ru.bellintegrator.offices.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.offices.dao.OfficesDAO;
import ru.bellintegrator.offices.model.Offices;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficesDAOImpl implements OfficesDAO {

    private final EntityManager em;

    @Autowired
    public OfficesDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Offices> all() {
        TypedQuery<Offices> query = em.createQuery("SELECT o FROM Offices o", Offices.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Offices loadById(Long id) {
        return em.find(Offices.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Offices loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Offices> criteria = builder.createQuery(Offices.class);

        Root<Offices> offices = criteria.from(Offices.class);
        criteria.where(builder.equal(offices.get("name"), name));

        TypedQuery<Offices> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Offices offices) {
        em.persist(offices);
    }
}
