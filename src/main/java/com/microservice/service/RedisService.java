package com.microservice.service;

import com.microservice.model.InteractionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void updateViralityScore(Long postId, InteractionType interactionType) {
        String key = "post:" + postId + ":virality_score";
        long amount = switch (interactionType) {
            case LIKE -> 20;
            case COMMENT -> 50;
            case BOT_REPLY -> 1;
        };

        if (amount > 0) {
            redisTemplate.opsForValue().increment(key, amount);
        }
    }

    public boolean isDepthExceeded(int depthLevel) {
        return depthLevel > 20;
    }

    public boolean isBotLimitExceeded(Long postId) {
        String key = "post:" + postId + ":botCount";
        Long count = redisTemplate.opsForValue().increment(key);
        if (count != null && count > 100) {
            redisTemplate.opsForValue().decrement(key);
            return true;
        }
        return false;
    }
    public boolean isCooldownActive(Long botId, Long userId){
        String key = "cooldown:bot: " + botId + ":human " + userId;
        boolean isKeyPresent = redisTemplate.hasKey(key);
        if (isKeyPresent) return true;
        redisTemplate.opsForValue().set(key,"1",10, TimeUnit.MINUTES);
        return false;
    }
    public void sendQueueNotification(Long userId, String message){
        String key = "NotificationCooldown:" + userId;
        boolean isKeyPresent =  redisTemplate.hasKey(key);
        if (isKeyPresent) {
            String newKey= "user:" + userId + ":pendingNotification";
            redisTemplate.opsForList().leftPush(newKey,message);
        }
        else  {
            System.out.println("Push notification sent to user: "+ userId);
            redisTemplate.opsForValue().set(key,"1",15, TimeUnit.MINUTES);
        }
    }
}
