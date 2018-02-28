package ru.bellintegrator.organizations.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.organizations.dao.OrganizationsDAO;
import ru.bellintegrator.organizations.model.Organizations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationsDAOImpl implements OrganizationsDAO{
    private final EntityManager em;

    @Autowired
    public OrganizationsDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organizations> all() {
        TypedQuery<Organizations> query = em.createQuery("SELECT o FROM Organizations o", Organizations.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organizations loadById(Long id) {
        return em.find(Organizations.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organizations loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organizations> criteria = builder.createQuery(Organizations.class);

        Root<Organizations> organizations = criteria.from(Organizations.class);
        criteria.where(builder.equal(organizations.get("name"), name));

        TypedQuery<Organizations> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organizations organizations) {
        em.persist(organizations);
    }
}
