package com.example.WDA_backend.Service;

import com.example.WDA_backend.Entity.UserStats;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import com.example.WDA_backend.Repository.UserStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean deleteUsers(String id) {
        // Kiểm tra người dùng có tồn tại trong cơ sở dữ liệu không
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true; // Xóa thành công
        }
        return false; // Người dùng không tồn tại
    }

    public UserStats getUserStatsByUsername(String username) {
        return userStatsRepo.findByUsername(username).orElse(null); // Tìm kiếm UserStats theo userId
    }

}
