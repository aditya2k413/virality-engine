package com.microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bot {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String personaDescription;
}
