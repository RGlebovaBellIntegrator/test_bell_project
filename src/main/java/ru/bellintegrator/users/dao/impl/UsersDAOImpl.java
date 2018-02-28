package ru.bellintegrator.users.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.users.dao.UsersDAO;
import ru.bellintegrator.users.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO{
    private final EntityManager em;

    @Autowired
    public UsersDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Users> all() {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Users loadById(Long id) {
        return em.find(Users.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Users loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Users> criteria = builder.createQuery(Users.class);

        Root<Users> users = criteria.from(Users.class);
        criteria.where(builder.equal(users.get("name"), name));

        TypedQuery<Users> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Users users) {
        em.persist(users);
    }
}
