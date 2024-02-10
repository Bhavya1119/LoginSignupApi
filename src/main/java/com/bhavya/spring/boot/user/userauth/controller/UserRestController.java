package com.bhavya.spring.boot.user.userauth.controller;

import com.bhavya.spring.boot.user.userauth.entity.Role;
import com.bhavya.spring.boot.user.userauth.entity.User;
import com.bhavya.spring.boot.user.userauth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;
    //constructor injection
    @Autowired
    public UserRestController(UserService theuserService){
        userService = theuserService;
    }

    //endpoint for displaying all the users
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.findAll();
    }
    //endpoint for getting session details of users

    @GetMapping("/users/{userName}")
    public User getUserbyName(@PathVariable String userName){
        User dbUser = userService.findByName(userName);
        if(dbUser == null){
            throw new RuntimeException("User not found with name - " +userName);
        }
        return dbUser;
    }
    //endpoint for signup process
    @PostMapping("/signup")
    public User signUp(@RequestBody User user, HttpSession session){
        //getting session id
        String sessionId = session.getId();
        long creationTime = session.getCreationTime();
    //if by mistake the user adds the id as well
        user.setId(0);
        user.setToken(sessionId);
        user.setSessionTime(creationTime);
        User dbUser = userService.save(user);
        return dbUser;
    }
    //function to check Name field - did not use regexp
    private boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    /* In this login endpoint , when an admin tries to login using the
    username and role, the user will be able to see all the details
    including surprise but a default user cannot view surprise
     */

    @PostMapping("/login")
    public User login (@RequestParam String name, @RequestParam Role role,HttpSession session, HttpServletRequest request){
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name format !");
        }
        if(!request.isRequestedSessionIdValid())
        {
            throw new IllegalStateException("Session Expired !");
        }
        String sessionId = session.getId();
        //getting session time in seconds
        long creationTime = session.getCreationTime()/1000;
        long timeout = request.getSession().getMaxInactiveInterval();
        User dbUser = userService.findByNameAndRole(name,role);
        if(dbUser.getRole()== Role.ADMIN){
           return dbUser;
        }
        else {
            dbUser.setSurprise(null);
            }

            return dbUser;
        }
    }

