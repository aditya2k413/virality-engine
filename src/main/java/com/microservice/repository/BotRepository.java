package com.microservice.repository;

import com.microservice.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepository extends JpaRepository<Bot,Long> {
}
