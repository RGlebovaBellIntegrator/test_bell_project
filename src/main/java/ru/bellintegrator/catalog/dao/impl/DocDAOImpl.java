package ru.bellintegrator.catalog.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.DocDAO;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.practice.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Doc> criteria = builder.createQuery(Doc.class);

        Root<Doc> doc = criteria.from(Doc.class);
        criteria.where(builder.equal(doc.get("code"), code));

        TypedQuery<Doc> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public void save(Doc doc) {
        em.persist(doc);
    }
}
