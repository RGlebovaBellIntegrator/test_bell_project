package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.CountriesDAO;
import ru.bellintegrator.catalog.model.Countries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountriesDAOImpl implements CountriesDAO{
    private final EntityManager em;

    @Autowired
    public CountriesDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Countries> all() {
        TypedQuery<Countries> query = em.createQuery("SELECT c FROM Countries c", Countries.class);
        return query.getResultList();
    }

    @Override
    public Countries loadByCode(Integer code) {
        return em.find(Countries.class, code);
    }
}
