package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.CountryDAO;
import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.optional.NoFoundException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public Country loadByCode(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

        Root<Country> countryRoot = criteria.from(Country.class);
        criteria.where(builder.equal(countryRoot.get("code"), code));

        TypedQuery<Country> query = em.createQuery(criteria);
        try {
            return query.getSingleResult();
        }
        catch (Exception ex) {
            throw new NoFoundException("Нет такой страны", ex);
        }
    }

    @Override
    public void save(Country country) {
        em.persist(country);
    }


    @Override
    public Country loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

        Root<Country> countryRoot = criteria.from(Country.class);
        criteria.where(builder.equal(countryRoot.get("name"), name));

        TypedQuery<Country> query = em.createQuery(criteria);
        try {
            return query.getSingleResult();
        }
        catch (Exception ex) {
            throw new NoFoundException("Нет такой страны", ex);
        }
    }
}
