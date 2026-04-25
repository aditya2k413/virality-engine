package com.microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="users")
@Entity
public class User {
    private String username;
    @Id
    @GeneratedValue
    private Long id;
    private Boolean isPremium;
}
