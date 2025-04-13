package com.example.WDA_backend.Repository;

import com.example.WDA_backend.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<Users, String> {
    public boolean existsByUsername(String username);
    public Optional<Users> findByUsername(String username);
}
