package com.example.WDA_backend.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LeaderboardService {

    private static final String LEADERBOARD_KEY = "leaderboard";
    private static final long MAX_PLAYERS = 10;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void addPlayer(String username, double score) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        // Thêm hoặc cập nhật điểm số
        zSetOps.add(LEADERBOARD_KEY, username, score);

        // Giới hạn chỉ giữ lại top 10: xóa những người có thứ hạng thấp hơn
        long currentSize = zSetOps.zCard(LEADERBOARD_KEY);
        if (currentSize > MAX_PLAYERS) {
            zSetOps.removeRange(LEADERBOARD_KEY, 0, currentSize - MAX_PLAYERS - 1);
        }
    }

    public Set<ZSetOperations.TypedTuple<String>> getTopPlayers() {
        return redisTemplate.opsForZSet()
                .reverseRangeWithScores(LEADERBOARD_KEY, 0, MAX_PLAYERS - 1);
    }
}
