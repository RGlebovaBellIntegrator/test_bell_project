package ru.bellintegrator.offices.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.offices.dao.OfficeDAO;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.optional.PersistException;
import ru.bellintegrator.organization.dao.OrganizationDAO;
import ru.bellintegrator.organization.dao.impl.OrganizationDAOImpl;
import ru.bellintegrator.organization.model.Organization;
import ru.bellintegrator.user.model.Employe;

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

    private final OrganizationDAO organizationDAO;

    @Autowired
    public OfficeDAOImpl(EntityManager em, OrganizationDAO organizationDAO) {
        this.em = em;
        this.organizationDAO = organizationDAO;
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
        try {
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new NoFoundException("Офис не найден", ex);
        }
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

        if (name != null) {
            Predicate p = builder.equal(officeRoot.get("name"), name);
            predicate = builder.and(predicate, p);
        }

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
    public Long save(Office office) {
        try {
            em.persist(office);
            return office.getId();
        } catch (Exception ex) {
            throw new PersistException("Не удалось сохранить офис", ex);
        }
    }


    @Override
    public void update(Long id, String name, String address, String phone, Boolean isActive) {
        Office office = loadById(id);
        if (office==null)
            throw new NoFoundException("Офис с указанным id не найдена");

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
    }

    @Override
    public void delete(Long id) {
        Office office = loadById(id);
        if (office!=null){
            if (office.getOrganization()!=null)
                office.getOrganization().removeOffice(office);

            for (Employe employe: office.getEmploye()) {
                employe.setOffice(null);
            }
            em.remove(office);

        }
        else
            throw new NoFoundException("Нет такого id");
    }

    @Override
    public Organization findOrgById(Long id)
    {
        return organizationDAO.loadById(id);
    }
}
