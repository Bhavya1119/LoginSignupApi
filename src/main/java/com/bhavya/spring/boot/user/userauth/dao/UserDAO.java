package com.bhavya.spring.boot.user.userauth.dao;

import com.bhavya.spring.boot.user.userauth.entity.Role;
import com.bhavya.spring.boot.user.userauth.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findByName(String name);
    User findByNameAndRole(String name , Role role);
    User save (User theuser);
}
