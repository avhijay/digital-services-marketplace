package com.marketplace.user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUser {
    public String name;
    public String  email;

    public CreateUser(){}


    public CreateUser(String name, String email) {
        this.name = name;
        this.email = email;
    }


}
