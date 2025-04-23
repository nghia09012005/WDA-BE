package com.example.WDA_backend.Repository;

import com.example.WDA_backend.Entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

@Repository
public interface UserStatsRepo extends JpaRepository<UserStats, String> {

    Optional<UserStats> findByUserId(String userId);

    @Query("SELECT us FROM UserStats us WHERE us.user.username = :username")
    Optional<UserStats> findByUsername(String username);
}