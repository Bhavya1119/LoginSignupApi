package com.bhavya.spring.boot.user.userauth.service;

import com.bhavya.spring.boot.user.userauth.entity.Role;
import com.bhavya.spring.boot.user.userauth.entity.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByName( String name);
    User findByNameAndRole(String name , Role role);
    User save (User theuser);
}
