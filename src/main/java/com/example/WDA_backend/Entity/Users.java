package com.example.WDA_backend.Entity;

import jakarta.persistence.*;

import java.security.PrivateKey;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;


    @OneToOne(mappedBy = "user")
    private UserStats userStats;

    // Constructor mặc định cần thiết cho JPA
    public Users() {
    }

    public Users( String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
