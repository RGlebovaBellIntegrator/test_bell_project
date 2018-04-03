package ru.bellintegrator.organization.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.optional.PersistException;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.organization.view.OrganizationView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
        try {
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new NoFoundException("Организация не найдена", ex);
        }
    }

    @Override
    public List<Organization> filter(String name,String inn, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> organizationRoot = criteria.from(Organization.class);

        Predicate predicate = builder.conjunction();
        if (name != null) {
            Predicate p = builder.equal(organizationRoot.get("name"), name);
            predicate = builder.and(predicate, p);
        }

        if (inn != null) {
            Predicate p = builder.equal(organizationRoot.get("inn"), inn);
            predicate = builder.and(predicate, p);
        }

        if (isActive != null) {
            Predicate p = builder.equal(organizationRoot.get("isActive"), isActive);
            predicate = builder.and(predicate, p);
        }

        criteria.where(predicate);

        TypedQuery<Organization> query = em.createQuery(criteria);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistException("Ошибка чтения данных", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long save(Organization organization) {
        try {
            em.persist(organization);
            return organization.getId();
        } catch (Exception ex) {
            throw new PersistException("Не удалось сохранить организацию", ex);
        }
    }

    @Override
    public void update(OrganizationView organizationView) {
        Organization organization = loadById(organizationView.id);
        if (organization==null)
            throw new NoFoundException("Организация с указанным id не найдена");

        if (organizationView.name != null) {
            organization.setName(organizationView.name);
        }

        if (organizationView.fullName != null) {
            organization.setFullname(organizationView.fullName);
        }

        if (organizationView.inn != null) {
            organization.setInn(organizationView.inn);
        }

        if (organizationView.kpp != null) {
            organization.setKpp(organizationView.kpp);
        }

        if (organizationView.address != null) {
            organization.setAddress(organizationView.address);
        }

        if (organizationView.phone != null) {
            organization.setPhone(organizationView.phone);
        }

        if (organizationView.isActive != null) {
            organization.setIsActive(organizationView.isActive);
        }
    }

    @Override
    public void delete(Long id) {
        Organization organization = loadById(id);
        if (organization!=null){
            em.remove(organization);
        }
        else
            throw new NoFoundException("Нет такого id");
    }
}
