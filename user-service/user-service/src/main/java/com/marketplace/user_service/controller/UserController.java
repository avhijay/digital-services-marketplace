package com.marketplace.user_service.controller;

import com.marketplace.user_service.dto.CreateUser;
import com.marketplace.user_service.dto.UserDto;
import com.marketplace.user_service.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }


    @GetMapping("/test")
    public String test(){
        return "Working - user service";
    }





}
