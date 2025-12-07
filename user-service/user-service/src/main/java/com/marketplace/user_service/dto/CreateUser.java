package com.marketplace.user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUser {
    public String name;
    public String emailId;

    public CreateUser(){}

    public CreateUser(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
    }
}
