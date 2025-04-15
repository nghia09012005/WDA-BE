package com.example.WDA_backend.Service;

import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

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

}
