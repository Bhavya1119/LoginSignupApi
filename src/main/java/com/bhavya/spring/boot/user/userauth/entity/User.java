package com.bhavya.spring.boot.user.userauth.entity;

import jakarta.persistence.*;
import org.intellij.lang.annotations.Pattern;

@Entity
@Table(name = "user")
public class User {
    //defining the fields
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "surprise")
    private String surprise;
    @Column(name = "token")
    private String token;

    @Column(name = "session")
    private Long sessionTime;

    //defining constructors
    public User(){

    }

    public User(String name, Role role, String surprise, String token,Long sessionTime) {
        this.name = name;
        this.role = role;
        this.surprise = surprise;
        this.token = token;
        this.sessionTime = sessionTime;
    }
    //defining the getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSurprise() {
        return surprise;
    }

    public void setSurprise(String surprise) {
        this.surprise = surprise;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getSessionTime(){ return sessionTime;}

    public void setSessionTime(Long sessionTime){this.sessionTime = sessionTime;}

    //defining the toString() method


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", surprise='" + surprise + '\'' +
                ", token='" + token + '\'' +
                ", sessionTime=" + sessionTime +
                '}';
    }
}
