package ru.bellintegrator.offices.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.offices.dao.OfficeDAO;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.dao.impl.OrganizationDAOImpl;
import ru.bellintegrator.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> all() {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o", Office.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> offices = criteria.from(Office.class);
        criteria.where(builder.equal(offices.get("name"), name));

        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public List<Office> filter(Long orgId, String name,String phone, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> officeRoot = criteria.from(Office.class);

        Predicate predicate = builder.conjunction();

        if (orgId != null) {
            Predicate p = builder.equal(officeRoot.get("organization").get("id"), orgId);
            predicate = builder.and(predicate, p);
        }
        else throw new NullPointerException("orgId не инициализирован");

        if (name != null) {
            Predicate p = builder.equal(officeRoot.get("name"), name);
            predicate = builder.and(predicate, p);
        }
        else throw new NullPointerException("name не инициализирован");

        if (phone != null) {
            Predicate p = builder.equal(officeRoot.get("phone"), phone);
            predicate = builder.and(predicate, p);
        }

        if (isActive != null) {
            Predicate p = builder.equal(officeRoot.get("isActive"), isActive);
            predicate = builder.and(predicate, p);
        }

        criteria.where(predicate);

        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }


    @Override
    public void update(Long id, String name, String address, String phone, Boolean isActive) {
        Office office = loadById(id);
        if (office==null) {
            save(office);
        }
        else {
            if (name != null) {
                office.setName(name);
            }

            if (address != null) {
                office.setAddress(address);
            }

            if (phone != null) {
                office.setPhone(phone);
            }

            if (isActive != null) {
                office.setIsActive(isActive);
            }
        };
    }

    @Override
    public void delete(Long id) {
        Office office = em.find(Office.class, id);
        if (office!=null){
            em.remove(office);
        }
        else
            throw new NullPointerException("Нет такого id");
    }

    @Override
    public Organization findOrgById(Long id)
    {
        OrganizationDAO d = new OrganizationDAOImpl(em);
        Organization o = d.loadById(id);
        return o;
    }
}
