package com.bhavya.spring.boot.user.userauth.dao;

import com.bhavya.spring.boot.user.userauth.entity.Role;
import com.bhavya.spring.boot.user.userauth.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager entityManager;

    //constructor injection
    @Autowired
    public UserDAOImpl(EntityManager theentityManager)
    {
        entityManager = theentityManager;
    }
    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("From User",User.class);
        return theQuery.getResultList();
    }

    @Override
    public User findByName(String name) {
        User dbUser = entityManager.find(User.class,name);
        return dbUser;
    }

    @Override
    public User findByNameAndRole(String name, Role role) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.role = :role", User.class);
        query.setParameter("name", name);
        query.setParameter("role", role);
        return query.getSingleResult();
    }
    @Override
    public User save(User theuser) {
        User dbUser = entityManager.merge(theuser);
        return dbUser;
    }
}
