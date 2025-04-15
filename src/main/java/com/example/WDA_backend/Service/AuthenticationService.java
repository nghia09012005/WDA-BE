package com.example.WDA_backend.Service;

import com.example.WDA_backend.Configuration.JwtUtil;
import com.example.WDA_backend.Dtos.Request.SigninRequest;
import com.example.WDA_backend.Dtos.Request.SignupRequest;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;


    public boolean Signup(SignupRequest user){
        if(!repo.existsByUsername(user.getUsername())){
            String hashedPassword = encoder.encode(user.getPassword());
            repo.save(new Users(user.getUsername(), hashedPassword, user.getEmail()));
            return true;
        }
        return false;
    }

    public String Signin(SigninRequest user){
        Optional<Users> us = repo.findByEmail(user.getEmail());
        if (us.isPresent()) {
            Users com = us.get();
            if (encoder.matches(user.getPassword(), com.getPassword())) {
                return jwtUtil.generateToken(com.getUsername());
            }
        }
        return null;
    }
}
