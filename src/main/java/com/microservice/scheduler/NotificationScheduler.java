package com.microservice.scheduler;

import com.microservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class NotificationScheduler {
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(fixedRate = 300000)
    public void Sweeper() {
        Set<String> keys = redisTemplate.keys("user:*:pendingNotification");
        if (keys == null || keys.isEmpty()) return;
        for (String key : keys) {
            List<String> msg =redisTemplate.opsForList().range(key,0,-1);
            System.out.println("Summarized Push Notification: Bot X and "+ (msg.size() - 1) +" others interacted with your posts");
            redisTemplate.delete(key);

        }
    }
}
