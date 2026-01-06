package com.marketplace.user_service.entity;


import com.marketplace.user_service.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "email",nullable = false,unique = true ,length = 255)
    private String email;


    @Column(name = "full_name",nullable = false , length = 255)
    private String name;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

@Column(name = "user_id",nullable = false , length = 36)

private String uniqueUserId;

    @Column(name = "Status",nullable = false)
    private Status status;


    @PrePersist
    void onCreate(){
        createdAt=Instant.now();

    }

    @PreUpdate
    void onUpdate(){
        updatedAt=Instant.now();
    }



    public Users(){}

    public Users(Long id, String email, String name, String uniqueUserId, Status status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.uniqueUserId = uniqueUserId;
        this.status = status;
    }
}
