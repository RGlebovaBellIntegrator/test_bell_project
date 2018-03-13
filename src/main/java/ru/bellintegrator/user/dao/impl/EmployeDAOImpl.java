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
    public List<Employe> filter(String firstname, String secondname, String middlename,
                                String position, String doc_code, String office_id, String country_code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

        Root<Employe> employe = criteria.from(Employe.class);

        Predicate predicate = builder.conjunction();
        if (firstname != null) {
            Predicate p = builder.equal(employe.get("firstname"), firstname);
            predicate = builder.and(predicate, p);
        }

        if (secondname != null) {
            Predicate p = builder.equal(employe.get("secondname"), secondname);
            predicate = builder.and(predicate, p);
        }

        if (middlename != null) {
            Predicate p = builder.equal(employe.get("middlename"), middlename);
            predicate = builder.and(predicate, p);
        }

        if (position != null) {
            Predicate p = builder.equal(employe.get("position"), position);
            predicate = builder.and(predicate, p);
        }

        if (doc_code != null) {
            Predicate p = builder.equal(employe.get("doc").get("code"), doc_code);
            predicate = builder.and(predicate, p);
        }

        if (country_code != null) {
            Predicate p = builder.equal(employe.get("country").get("code"), country_code);
            predicate = builder.and(predicate, p);
        }

        if (office_id != null) {
            Predicate p = builder.equal(employe.get("office").get("id"), office_id);
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
    public void save(Employe employe) {
        em.persist(employe);
    }

    @Override
    public void update(Long id, String firstname, String secondname, String middlename,
                       String position, String phone, String doc_name, String doc_number,
                       String doc_date, String country_name, String country_code, Boolean isIdentified) {
        Employe employe = loadById(id);
        if (employe==null) {
            save(employe);
        }
        else {
            if (firstname != null) {
                employe.setFirstname(firstname);
            }

            if (secondname != null) {
                employe.setSecondname(secondname);
            }

            if (middlename != null) {
                employe.setMiddlename(middlename);
            }

            if (position != null) {
                employe.setStatement(position);
            }

            if (phone != null) {
                employe.setPhone(phone);
            }


            if (doc_name != null) {
                CriteriaBuilder builder = em.getCriteriaBuilder();
                CriteriaQuery<Doc> criteria = builder.createQuery(Doc.class);

                Root<Doc> person = criteria.from(Doc.class);
                criteria.where(builder.equal(person.get("name"), doc_name));

                TypedQuery<Doc> query = em.createQuery(criteria);

                Doc doc_temp = query.getSingleResult();

                if (doc_temp!=null) {
                    employe.setDoc(doc_temp);
                }
                else System.out.println("Нет документа");
            }

            if (doc_number != null) {
                employe.setStatement(doc_number);
            }

            if (doc_date != null) {
                employe.setPhone(doc_date);
            }

            if (country_name != null) {
                CriteriaBuilder builder = em.getCriteriaBuilder();
                CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

                Root<Country> person = criteria.from(Country.class);
                criteria.where(builder.equal(person.get("name"), country_name));

                TypedQuery<Country> query = em.createQuery(criteria);

                Country country_temp = query.getSingleResult();

                if (country_temp!=null) {
                    employe.setCountry(country_temp);
                }
                else System.out.println("Нет документа");;
            }

            if (country_code != null) {
                CriteriaBuilder builder = em.getCriteriaBuilder();
                CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

                Root<Country> person = criteria.from(Country.class);
                criteria.where(builder.equal(person.get("code"), country_code));

                TypedQuery<Country> query = em.createQuery(criteria);

                Country country_temp = query.getSingleResult();

                if (country_temp!=null) {
                    employe.setCountry(country_temp);
                }
                else System.out.println("Нет страны");;
            }

            if (isIdentified != null) {
                employe.setIsIdentified(isIdentified);
            }
            /*CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Employe> criteria = builder.createQuery(Employe.class);

            Root<Employe> r = criteria.from(Employe.class);

            Predicate predicate = builder.conjunction();
            if (employe.getFirstName() != null) {
                Predicate p = builder.equal(r.get("firstname"), employe.getFirstName());
                predicate = builder.and(predicate, p);
            }

            criteria.where(predicate);
            TypedQuery<Employe> query = em.createQuery(criteria);*/

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
    public Doc findDocId(Integer code) {
        DocDAO d = new DocDAOImpl(em);
        Doc doc = d.loadByCode(code);
        return doc;
    }


    @Override
    public Country findCountryId(Integer code) {
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
