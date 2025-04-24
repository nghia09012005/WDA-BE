package com.example.WDA_backend.Service;

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

}
