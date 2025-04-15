package com.example.WDA_backend.Dtos.Request;

public class SigninRequest {
    String password;
    String email;

    // Constructor mặc định cần thiết để Spring có thể deserialize
    public SigninRequest() {
    }

    public SigninRequest( String password, String email) {

        this.password = password;
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
