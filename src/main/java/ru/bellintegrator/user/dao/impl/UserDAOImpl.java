package ru.bellintegrator.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> all() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> users = criteria.from(User.class);
        criteria.where(builder.equal(users.get("name"), name));

        TypedQuery<User> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public User loadByLogin(String login, String password) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> users = criteria.from(User.class);
        criteria.where(builder.equal(users.get("login"), login), builder.equal(users.get("password"), password));

        TypedQuery<User> query = em.createQuery(criteria);

        try {
            return query.getSingleResult();
        }
        catch (Exception ex) {
            //запись в лог нужна
            return null;
        }
    }

    @Override
    public User loadByCode(String activateCode) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> users = criteria.from(User.class);
        criteria.where(builder.equal(users.get("code"), activateCode));

        TypedQuery<User> query = em.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public Boolean updateCode(String code)
    {
        try {
            loadByCode(code).setIsActive(true);
            return true;
        }
        catch (Exception ex) {
            //запись в лог нужна
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }
}
