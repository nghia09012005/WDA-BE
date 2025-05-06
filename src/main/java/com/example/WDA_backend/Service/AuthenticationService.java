package com.example.WDA_backend.Service;

import com.example.WDA_backend.Configuration.JwtUtil;
import com.example.WDA_backend.Dtos.Request.SigninRequest;
import com.example.WDA_backend.Dtos.Request.SignupRequest;
import com.example.WDA_backend.Dtos.Response.SigninResponse;
import com.example.WDA_backend.Entity.UserStats;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import com.example.WDA_backend.Repository.UserStatsRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private UserStatsRepo userStatsRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisService redis;


    public boolean Signup(SignupRequest user){
        if(!repo.existsByUsername(user.getUsername())){
            // Mã hóa mật khẩu và lưu người dùng
            String hashedPassword = encoder.encode(user.getPassword());
            Users newUser = new Users(user.getUsername(), hashedPassword);
            repo.save(newUser);

            // Tạo đối tượng UserStats với giá trị exp = 0 và money = 0
            UserStats userStats = new UserStats();
            userStats.setUser(newUser); // Gán người dùng vừa tạo
            userStats.setExp(0); // Giá trị mặc định
            userStats.setMoney(0); // Giá trị mặc định

            // Lưu UserStats
            userStatsRepo.save(userStats);

            return true;
        }
        return false;
    }

    public Optional<SigninResponse> Signin(SigninRequest user) {
        Optional<Users> us = repo.findByUsername(user.getUsername());
        if (us.isPresent()) {
            Users com = us.get();
            if (encoder.matches(user.getPassword(), com.getPassword())) {
                String token = jwtUtil.generateToken(com.getUsername(),com.getId());

                String username = com.getUsername();
                UserStats userStats = userStatsRepo.findByUsername(com.getUsername()).orElse(null);
                String val = String.valueOf(userStats.getMoney())+" "+String.valueOf(userStats.getExp());
                redis.setValue("user:" +username, val);
                System.out.println("user:"+username+" info:"+val);

                return Optional.of(new SigninResponse(token, com.getUsername()));
            }
        }
        return Optional.empty();
    }
}
