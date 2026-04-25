package com.microservice.controller;

import com.microservice.model.Bot;
import com.microservice.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bot")
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping
    public Bot createBot(@RequestBody Bot bot){
        return botService.createBot(bot);
    }
}
