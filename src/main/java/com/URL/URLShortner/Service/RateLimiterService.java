package com.URL.URLShortner.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean isAllowed(String ipAddress) {

        String key = "rate:" + ipAddress;

        Long count = redisTemplate.opsForValue()
                .increment(key);

        if (count == 1) {
            redisTemplate.expire(
                    key,
                    Duration.ofMinutes(1)
            );
        }

        return count <= 100;
    }
}
