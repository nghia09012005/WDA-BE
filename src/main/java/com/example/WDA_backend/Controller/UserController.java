package com.example.WDA_backend.Controller;

import com.example.WDA_backend.Dtos.Request.ItemRequest;
import com.example.WDA_backend.Dtos.Request.MoneyExpRequest;
import com.example.WDA_backend.Dtos.Response.ApiResponse;
import com.example.WDA_backend.Dtos.Response.UserResponseDto;
import com.example.WDA_backend.Entity.UserStats;
import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Service.RedisService;
import com.example.WDA_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Định nghĩa đường dẫn API cho người dùng
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    // API lấy danh sách tất cả người dùng
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getUsers(); // Trả về danh sách tất cả người dùng
    }

//    test redis
    @GetMapping("/test-redis")
    public String testRedis() {
        redisService.testRedisConnection();
        return "Done";
    }

    // Updated Controller Method
    @GetMapping("/uswst")
    public List<UserResponseDto> getAllUserwst() {
        return userService.getUsersWithStats(); // Fetch users with stats
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

    @GetMapping("/stats/{name}")
    public ResponseEntity<ApiResponse<UserStats>> getUserStats(@PathVariable String name) {
        UserStats userStats = userService.getUserStatsByUsername(name);

        if (userStats != null) {
            return new ResponseEntity<>(new ApiResponse<>("1000", userStats), HttpStatus.OK); // Trả về thông tin UserStats
        }
        return new ResponseEntity<>(new ApiResponse<>("1003", null  ), HttpStatus.NOT_FOUND); // Trường hợp không tìm thấy người dùng
    }

    @PostMapping("/stats/set")
    public ResponseEntity<ApiResponse<UserStats>> setUserStats(@RequestBody ItemRequest request){
        UserStats userStats = userService.setStatsByUsername(request);
        if(userStats != null){
            return new ResponseEntity<>(new ApiResponse<>("1000", userStats), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("1003", null  ), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/stats/me")
    public ResponseEntity<ApiResponse<UserStats>> setUserMe(@RequestBody MoneyExpRequest request){
        UserStats userStats = userService.setMoneyExp(request);
        if(userStats != null){
            return new ResponseEntity<>(new ApiResponse<>("1000", userStats), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("1003", null  ), HttpStatus.NOT_FOUND);
    }


}
