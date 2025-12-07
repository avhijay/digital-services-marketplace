package com.marketplace.user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private Long userId;
    private String name;
    private String emailId;

    public UserDto(){}


    public UserDto(Long id, String name, String email) {
        this.userId = id;
        this.name = name;
        this.emailId = email;
    }


}
