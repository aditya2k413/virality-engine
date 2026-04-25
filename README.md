# Virality Engine - Grid07 Backend Assignment
## Overview
A Spring Boot microservice that serves as a backend API for a social platform. 
It uses PostgreSQL for data storage and Redis for real-time virality scoring, 
atomic bot guardrails, and a scheduled notification system.
## Tech Stack
- Java 17, Spring Boot 3.x
- PostgreSQL (via Docker)
- Redis (via Docker)
## How to Run
1. Start Docker containers:
2. docker-compose up -d
3. Configure `[application.properties](http://application.properties)` using `[application.properties](http://application.properties).example` as reference
4. Run the Spring Boot application from IntelliJ or
## API Endpoints
- POST /api/users - Create a user
- POST /api/bot - Create a bot
- POST /api/posts - Create a post
- POST /api/posts/{postId}/comments - Add a comment
- POST /api/posts/{postId}/like - Like a post
## Thread Safety
To stop problems with the bot reply limit I used something called Redis INCR. This thing increases a counter. Gives me the new number all at once. I do not check the number. Then increase it because that can cause problems.
Instead I increase the number first. Then I check what it is. If the number is, than 100 I decrease it back down and send an error message with the number 429. This way I can be sure that the bot only replies 100 times even if lots of people are asking it questions at the time like 200 people all at once. The bot replies are limited to 100 so the Redis INCR helps with the bot replies.
