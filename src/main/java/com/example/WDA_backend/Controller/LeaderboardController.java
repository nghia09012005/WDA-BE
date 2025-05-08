package com.example.WDA_backend.Controller;

import com.example.WDA_backend.Service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlayer(@RequestParam String username, @RequestParam double score) {
//        example request: ...../add?username=nghia&score=100
        leaderboardService.addPlayer(username, score);
        return ResponseEntity.ok("Player added");
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopPlayers() {
        return ResponseEntity.ok(leaderboardService.getTopPlayers());
    }
}
