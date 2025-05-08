package com.example.WDA_backend.Service;

import com.example.WDA_backend.Dtos.Request.ItemRequest;
import com.example.WDA_backend.Dtos.Request.MoneyExpRequest;
import com.example.WDA_backend.Dtos.Response.UserResponseDto;
import com.example.WDA_backend.Entity.UserStats;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import com.example.WDA_backend.Repository.UserStatsRepo;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.BooleanDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private UserStatsRepo userStatsRepo;

    @Autowired
    private RedisService redis;

    // Trả về danh sách tất cả người dùng
    public List<Users> getUsers() {
        return repo.findAll(); // Trả về tất cả người dùng từ DB
    }

    public List<UserResponseDto> getUsersWithStats() {
        List<Users> users = repo.findAll(); // Fetch all users
        return users.stream().map(user -> {
            UserStats stats = userStatsRepo.findByUserId(user.getId()).orElse(null);
            return new UserResponseDto(user, stats); // Combine user and stats
        }).collect(Collectors.toList());
    }


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

        UserStats userStats = userStatsRepo.findByUsername(username).orElse(null); // Tìm kiếm UserStats theo userId
        if(userStats == null){return null;}
        String val = redis.getValue("user:"+username);
        System.out.println("val:"+val);
        String[] parts = val.split(" ");
        double money = Double.parseDouble(parts[0]);
        int exp = Integer.parseInt(parts[1]);
        userStats.setMoney(money);
        userStats.setExp(exp);
        redis.deleteFromRedis("user:"+username);
        userStatsRepo.save(userStats);
        return userStats;
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

        String val = redis.getValue("user:"+request.getUsername());
        System.out.println("val:"+val);
        String[] parts = val.split(" ");
        double money = Double.parseDouble(parts[0]);
        int exp = Integer.parseInt(parts[1]);
        userStats.setMoney(money);
        userStats.setExp(exp);

        userStatsRepo.save(userStats);


        return userStats;
    }

    public UserStats setMoneyExp(MoneyExpRequest request) {
        String object = request.getObject();
        String redisKey = "user:" + request.getUsername();

        // Kiểm tra dữ liệu có trong Redis không
        String val = redis.getValue(redisKey);
        boolean check = redis.existsInRedis(redisKey);
        System.out.println("check:"+check);
        if (!check  ) {
            // Dữ liệu không có trong Redis, truy vấn từ DB
            UserStats userStats = userStatsRepo.findByUsername(request.getUsername()).orElse(null);
            if (userStats == null) {
                return null; // Người dùng không tồn tại trong DB
            }

            // Cập nhật dữ liệu trong DB
            if (object.equals("money")) {
                userStats.setMoney(userStats.getMoney() + request.getAmount());
            } else {
                userStats.setExp(userStats.getExp() + request.getAmount());
            }

            // Lưu vào DB
            userStatsRepo.save(userStats);

            // Lưu lại vào Redis để giảm tải DB cho lần sau
            redis.setValue(redisKey, userStats.getMoney() + " " + userStats.getExp());

            return userStats;
        } else {
            // Dữ liệu có trong Redis, xử lý trực tiếp
            String[] parts = val.split(" ");
            double money = Double.parseDouble(parts[0]);
            int exp = Integer.parseInt(parts[1]);

            if (object.equals("money")) {
                money += request.getAmount();
            } else {
                exp += request.getAmount();
            }

            // Cập nhật lại Redis
            redis.setValue(redisKey, money + " " + exp);

          }

            return new UserStats();

    }


}
