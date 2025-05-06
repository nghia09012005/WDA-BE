package com.example.WDA_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteFromRedis(String key) {
        redisTemplate.delete(key);
    }

    public boolean existsInRedis(String key) {
        return redisTemplate.hasKey(key);
    }

    public void testRedisConnection() {
        // Lưu trữ giá trị vào Redis
        redisTemplate.opsForValue().set("testKey", "Hello Redis");

        // Lấy giá trị từ Redis
        String value = redisTemplate.opsForValue().get("testKey");
        System.out.println("Value from Redis: " + value);
    }

}
