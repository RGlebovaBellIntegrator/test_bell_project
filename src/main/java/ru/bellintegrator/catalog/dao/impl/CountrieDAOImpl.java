package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.CountrieDAO;
import ru.bellintegrator.catalog.model.Countrie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountrieDAOImpl implements CountrieDAO {
    private final EntityManager em;

    @Autowired
    public CountrieDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Countrie> all() {
        TypedQuery<Countrie> query = em.createQuery("SELECT c FROM Countrie c", Countrie.class);
        return query.getResultList();
    }

    @Override
    public Countrie loadByCode(Integer code) {
        return em.find(Countrie.class, code);
    }

    @Override
    public void save(Countrie countrie) {
        em.persist(countrie);
    }
}
