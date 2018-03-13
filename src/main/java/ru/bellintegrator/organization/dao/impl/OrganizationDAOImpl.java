package ru.bellintegrator.organization.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.model.Organization;

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
        return query.getSingleResult();
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
        else throw new NullPointerException("name не инициализирован");;

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
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public void update(Long id, String name, String fullName, String inn,
                       String kpp, String address, String phone, Boolean isActive) {
        Organization organization = loadById(id);
        if (organization==null) {
            save(organization);
        }
        else {
            if (name != null) {
                organization.setName(name);
            }

            if (fullName != null) {
                organization.setFullname(fullName);
            }

            if (inn != null) {
                organization.setInn(inn);
            }

            if (kpp != null) {
                organization.setKpp(kpp);
            }

            if (address != null) {
                organization.setAddress(address);
            }

            if (phone != null) {
                organization.setPhone(phone);
            }

            if (isActive != null) {
                organization.setIsActive(isActive);
            }
        };
    }

    @Override
    public void delete(Long id) {
        Organization organization = em.find(Organization.class, id);
        if (organization!=null){
            em.remove(organization);
        }
        else
            throw new NullPointerException("Нет такого id");
    }
}
