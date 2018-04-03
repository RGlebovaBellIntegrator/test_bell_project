package ru.bellintegrator.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.optional.NoFoundException;
import ru.bellintegrator.optional.PersistException;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.model.User;

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
        try {
            return em.find(User.class, id);
        }
        catch (Exception ex) {
            throw new NoFoundException("Пользователь не найден", ex);
        }
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
        try {
            return query.getSingleResult();
        }
        catch (Exception ex) {
            throw new NoFoundException("Пользователь не найден", ex);
        }
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
            throw new NoFoundException("Сочетание login/password не найдены", ex);
        }
    }

    @Override
    public User loadByCode(String activateCode) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> users = criteria.from(User.class);
        criteria.where(builder.equal(users.get("code"), activateCode));

        TypedQuery<User> query = em.createQuery(criteria);

        try {
            return query.getSingleResult();
        }
        catch (Exception ex) {
            throw new NoFoundException("Не найден указанный код.", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        try {
            em.persist(user);
        }
        catch (Exception ex) {
            throw new PersistException("Не удалось сохранить пользователя", ex);
        }
    }
}
