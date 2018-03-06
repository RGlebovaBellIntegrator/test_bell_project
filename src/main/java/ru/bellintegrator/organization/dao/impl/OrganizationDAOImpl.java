package ru.bellintegrator.organization.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
    private final EntityManager em;

    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> organizations = criteria.from(Organization.class);
        criteria.where(builder.equal(organizations.get("name"), name));

        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }
}
