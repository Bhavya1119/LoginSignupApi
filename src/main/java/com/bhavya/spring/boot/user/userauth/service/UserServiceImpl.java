package com.bhavya.spring.boot.user.userauth.service;

import com.bhavya.spring.boot.user.userauth.dao.UserDAO;
import com.bhavya.spring.boot.user.userauth.entity.Role;
import com.bhavya.spring.boot.user.userauth.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDAO userDAO;
    //constructor injection
    @Autowired
    public UserServiceImpl(UserDAO theuserDAO)
    {
        userDAO = theuserDAO;
    }
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }
    @Override
    public User findByNameAndRole(String name, Role role) {
        return userDAO.findByNameAndRole(name, role);
    }
    @Transactional
    @Override
    public User save(User theuser) {
        return userDAO.save(theuser);
    }
}
