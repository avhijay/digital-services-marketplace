package com.marketplace.user_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email",nullable = false,unique = true ,length = 255)
    private String email;


    @Column(name = "full_name",nullable = false , length = 255)
    private String name;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


    @PrePersist
    void onCreate(){
        createdAt=Instant.now();

    }

    @PreUpdate
    void onUpdate(){
        updatedAt=Instant.now();
    }



    public Users(){}


    public Users(String email, String name, Instant createdAt, Instant updatedAt) {
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
