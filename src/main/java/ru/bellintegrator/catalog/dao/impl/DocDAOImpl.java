package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.DocDAO;
import ru.bellintegrator.catalog.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocDAOImpl implements DocDAO {

    private final EntityManager em;

    @Autowired
    public DocDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Doc> all(){
        TypedQuery<Doc> query = em.createQuery("SELECT o FROM Doc o", Doc.class);
        return query.getResultList();
    }

    @Override
    public Doc loadByCode(Integer code) {
        return em.find(Doc.class, code);
    }

    @Override
    public void save(Doc doc) {
        em.persist(doc);
    }
}
