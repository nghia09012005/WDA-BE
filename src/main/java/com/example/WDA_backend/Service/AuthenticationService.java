package com.example.WDA_backend.Service;

import com.example.WDA_backend.Dtos.Request.SigninRequest;
import com.example.WDA_backend.Dtos.Request.SignupRequest;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepo repo;


    public boolean Signup(SignupRequest user){
        if(!repo.existsByUsername(user.getUsername())){
            repo.save(new Users(user.getUsername(), user.getPassword(), user.getEmail()));
            return true;
        }
        return false;
    }

    public boolean Signin(SigninRequest user){
        Optional<Users> us = repo.findByEmail(user.getEmail());
        if (us.isPresent()) {
            Users com = us.get();
            return com.getPassword().equals(user.getPassword());
        }

        return false;
    }
}
