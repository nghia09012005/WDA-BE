package com.example.WDA_backend.Service;

import com.example.WDA_backend.Dtos.Request.ItemRequest;
import com.example.WDA_backend.Dtos.Request.MoneyExpRequest;
import com.example.WDA_backend.Entity.UserStats;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import com.example.WDA_backend.Repository.UserStatsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private UserStatsRepo userStatsRepo;

    // Trả về danh sách tất cả người dùng
    public List<Users> getUsers() {
        return repo.findAll(); // Trả về tất cả người dùng từ DB
    }

    @Transactional
    public boolean deleteUsers(String id) {
        if (repo.existsById(id)) {
            Optional<Users> userOptional = repo.findById(id);
            userOptional.ifPresent(user -> {
                userStatsRepo.deleteByUserId(user.getId()); // Xóa UserStats liên quan đến User
            });
            repo.deleteById(id); // Xóa người dùng
            return true; // Xóa thành công
        }
        return false; // Người dùng không tồn tại
    }

    public UserStats getUserStatsByUsername(String username) {
        return userStatsRepo.findByUsername(username).orElse(null); // Tìm kiếm UserStats theo userId
    }

    public UserStats setStatsByUsername(ItemRequest request){
        String item = request.getItem();
        UserStats userStats = userStatsRepo.findByUsername(request.getUsername()).orElse(null);
        if(userStats == null){return null ;}
        if ("congchieng".equals(item)) {
            userStats.setCongchieng(true);
        } else if ("co".equals(item)) {
            userStats.setCo(true);
        } else if ("thu".equals(item)) {
            userStats.setThu(true);
        } else if ("tranh".equals(item)) {
            userStats.setTranh(true);
        } else if ("quanho".equals(item)) {
            userStats.setQuanho(true);
        } else if ("trongdong".equals(item)) {
            userStats.setTrongdong(true);
        }
        userStatsRepo.save(userStats);
        return userStats;
    }

    public UserStats setMoneyExp(MoneyExpRequest request){
        String object  = request.getObject();
        UserStats userStats = userStatsRepo.findByUsername(request.getUsername()).orElse(null);
        if(userStats == null){return null ;}
        if(object.equals("money")){
            userStats.setMoney(userStats.getMoney()+request.getAmount());
        }
        else{
            userStats.setExp(userStats.getExp()+request.getAmount());
        }
        userStatsRepo.save(userStats);
        return userStats;
    }

}
