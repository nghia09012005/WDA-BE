package com.example.WDA_backend.Controller;

import com.example.WDA_backend.Dtos.Response.ApiResponse;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Định nghĩa đường dẫn API cho người dùng
public class UserController {

    @Autowired
    private UserService userService;

    // API lấy danh sách tất cả người dùng
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getUsers(); // Trả về danh sách tất cả người dùng
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUsers(id);

        if (isDeleted) {
            return new ApiResponse("1000", "User deleted successfully");
        } else {
            return new ApiResponse("1001", "User not found");
        }
    }


}
