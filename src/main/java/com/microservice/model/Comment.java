package com.microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    private  Long id;
    private Long postId;
    private String authorType;
    private Long authorId;
    private Integer depthLevel;
    private String content;
    private LocalDateTime createdAt;
}
