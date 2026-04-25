package com.microservice.service;

import com.microservice.model.Bot;
import com.microservice.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {
    @Autowired
    private BotRepository botRepository;

    public Bot createBot(Bot bot){
        return botRepository.save(bot);
    }

}
