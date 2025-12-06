package com.marketplace.order_service.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {


    private Long userId;
    private String  name;
    private String emailId;

    public UserDto(){}

    public UserDto(Long userId, String name, String emailId) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
    }

}

