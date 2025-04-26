package com.example.WDA_backend.Dtos.Response;

import com.example.WDA_backend.Entity.Users;
import com.example.WDA_backend.Entity.UserStats;

public class UserResponseDto {
    private Users user;
    private UserStats stats;

    public UserResponseDto(Users user, UserStats stats) {
        this.user = user;
        this.stats = stats;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserStats getStats() {
        return stats;
    }

    public void setStats(UserStats stats) {
        this.stats = stats;
    }
}