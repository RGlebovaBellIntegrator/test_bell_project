package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.DocsDAO;
import ru.bellintegrator.catalog.model.Docs;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocsDAOImpl implements DocsDAO{

    private final EntityManager em;

    @Autowired
    public DocsDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Docs> all(){
        TypedQuery<Docs> query = em.createQuery("SELECT d FROM Docs d", Docs.class);
        return query.getResultList();
    }

    @Override
    public Docs loadByCode(Integer code) {
        return em.find(Docs.class, code);
    }

}
