package com.example.WDA_backend.Dtos.Request;

public class SignupRequest {
    private String username;
    private String password;


    // Constructor mặc định cần thiết để Spring có thể deserialize
    public SignupRequest() {
    }

    public SignupRequest(String username, String password, String email) {
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

}
