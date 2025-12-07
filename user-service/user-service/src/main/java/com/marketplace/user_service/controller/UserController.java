package com.marketplace.user_service.controller;

import com.marketplace.user_service.dto.CreateUser;
import com.marketplace.user_service.dto.UserDto;
import com.marketplace.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    private final  UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }


    @GetMapping("/test")
    public String test(){
        return "Working - user service";
    }



    @PostMapping
    public  ResponseEntity<UserDto>createUser(@RequestBody CreateUser request){

       UserDto users =  userService.createUser(request.getName(),request.getEmailId());

        return ResponseEntity.ok(users);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUser(@PathVariable Long userId){
        UserDto users = userService.getUserById(userId);
        return ResponseEntity.ok(users);
    }


}
