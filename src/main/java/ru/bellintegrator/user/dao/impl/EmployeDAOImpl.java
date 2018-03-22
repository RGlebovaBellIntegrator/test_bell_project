package ru.bellintegrator.user.dao.impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.catalog.dao.CountryDAO;
import ru.bellintegrator.catalog.dao.DocDAO;
import ru.bellintegrator.catalog.dao.impl.CountrieDAOImpl;
import ru.bellintegrator.catalog.dao.impl.DocDAOImpl;
import ru.bellintegrator.catalog.model.Country;
import ru.bellintegrator.catalog.model.Doc;
import ru.bellintegrator.offices.dao.OfficeDAO;
import ru.bellintegrator.offices.dao.impl.OfficeDAOImpl;
import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.user.dao.EmployeDAO;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.Employe;
import ru.bellintegrator.user.model.User;
import ru.bellintegrator.user.view.EmployeView;
import ru.bellintegrator.user.view.UserView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Repository
public class EmployeDAOImpl implements EmployeDAO {
    private final EntityManager em;

    @Autowired
    public EmployeDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employe> all() {
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e", Employe.class);
        return query.getResultList();
    }

    @Override
    public List<Employe> filter(EmployeView employeView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

        Root<Employe> employe = criteria.from(Employe.class);

        Predicate predicate = builder.conjunction();
        if (employeView.fullName != null) {
            Predicate p = builder.equal(employe.get("firstname"), employeView.fullName);
            predicate = builder.and(predicate, p);
        }

        if (employeView.secondName != null) {
            Predicate p = builder.equal(employe.get("secondname"), employeView.secondName);
            predicate = builder.and(predicate, p);
        }

        if (employeView.middleName != null) {
            Predicate p = builder.equal(employe.get("middlename"), employeView.middleName);
            predicate = builder.and(predicate, p);
        }

        if (employeView.position != null) {
            Predicate p = builder.equal(employe.get("position"), employeView.position);
            predicate = builder.and(predicate, p);
        }

        if (employeView.docCode != null) {
            Predicate p = builder.equal(employe.get("doc").get("code"), employeView.docCode);
            predicate = builder.and(predicate, p);
        }

        if (employeView.citizenshipCode != null) {
            Predicate p = builder.equal(employe.get("country").get("code"), employeView.citizenshipCode);
            predicate = builder.and(predicate, p);
        }

        if (employeView.officeId != null) {
            Predicate p = builder.equal(employe.get("office").get("id"), employeView.officeId);
            predicate = builder.and(predicate, p);
        }
        else throw new NullPointerException("office_id не инициализирован");;

        criteria.where(predicate);

        TypedQuery<Employe> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employe loadById(Long id) {
        return em.find(Employe.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employe loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

        Root<Employe> users = criteria.from(Employe.class);
        criteria.where(builder.equal(users.get("name"), name));

        TypedQuery<Employe> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long save(Employe employe) {
        em.persist(employe);
        return  employe.getId();
    }

    @Override
    public void update(EmployeView employeView) {
        Employe employe = loadById(employeView.id);
        if (employe==null) {
            save(employe);
        }
        else {
            if (employeView.firstName != null) {
                employe.setFirstname(employeView.firstName);
            }

            if (employeView.secondName != null) {
                employe.setSecondname(employeView.secondName);
            }

            if (employeView.middleName != null) {
                employe.setMiddlename(employeView.middleName);
            }

            if (employeView.position != null) {
                employe.setStatement(employeView.position);
            }

            if (employeView.phone != null) {
                employe.setPhone(employeView.phone);
            }


            if (employeView.docName != null) {
                CriteriaBuilder builder = em.getCriteriaBuilder();
                CriteriaQuery<Doc> criteria = builder.createQuery(Doc.class);

                Root<Doc> person = criteria.from(Doc.class);
                criteria.where(builder.equal(person.get("name"), employeView.docName));

                TypedQuery<Doc> query = em.createQuery(criteria);

                Doc doc_temp = query.getSingleResult();
                if (doc_temp!=null) {
                    employe.setDoc(doc_temp);
                }
                else System.out.println("Нет документа");
            }

            if (employeView.docNumber != null) {
                employe.setStatement(employeView.docNumber);
            }

            if (employeView.docDate != null) {
                employe.setDocDate(employeView.docDate);
            }

            if (employeView.citizenshipName != null) {
                CriteriaBuilder builder = em.getCriteriaBuilder();
                CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

                Root<Country> person = criteria.from(Country.class);
                criteria.where(builder.equal(person.get("name"), employeView.citizenshipName));

                TypedQuery<Country> query = em.createQuery(criteria);

                Country country_temp = query.getSingleResult();

                if (country_temp!=null) {
                    employe.setCountry(country_temp);
                }
                else System.out.println("Нет документа");;
            }

            if (employeView.citizenshipCode != null) {
                CriteriaBuilder builder = em.getCriteriaBuilder();
                CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

                Root<Country> person = criteria.from(Country.class);
                criteria.where(builder.equal(person.get("code"), employeView.citizenshipCode));

                TypedQuery<Country> query = em.createQuery(criteria);

                Country country_temp = query.getSingleResult();

                if (country_temp!=null) {
                    employe.setCountry(country_temp);
                }
                else System.out.println("Нет страны");;
            }

            if (employeView.isIdentified != null) {
                employe.setIsIdentified(employeView.isIdentified);
            }
        };
    }

    @Override
    public void delete(Long id) {
        Employe employe = em.find(Employe.class, id);
        if (employe!=null){
            em.remove(employe);
        }
        else
            throw new NullPointerException("Нет такого id");
    }

    @Override
    public Doc findDocId(String code) {
        DocDAO d = new DocDAOImpl(em);
        Doc doc = d.loadByCode(code);
        return doc;
    }


    @Override
    public Country findCountryId(String code) {
        CountryDAO d = new CountrieDAOImpl(em);
        Country c = d.loadByCode(code);
        return c;
    }

    @Override
    public Office findOfficeById(Long id) {
        OfficeDAO d = new OfficeDAOImpl(em);
        Office o = d.loadById(id);
        return o;
    }
}
