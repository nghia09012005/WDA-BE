package com.example.WDA_backend.Dtos.Request;

public class SigninRequest {
    String password;
    String username;

    // Constructor mặc định cần thiết để Spring có thể deserialize
    public SigninRequest() {
    }

    public SigninRequest( String password, String username  ) {

        this.password = password;
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
}
