package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.CountryDAO;
import ru.bellintegrator.catalog.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountrieDAOImpl implements CountryDAO {
    private final EntityManager em;

    @Autowired
    public CountrieDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Country> all() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    @Override
    public Country loadByCode(Integer code) {
        return em.find(Country.class, code);
    }

    @Override
    public void save(Country country) {
        em.persist(country);
    }
}
