package com.marketplace.user_service.controller;

import com.marketplace.user_service.dto.InternalUserDto;
import com.marketplace.user_service.dto.UserAdminCreateDto;
import com.marketplace.user_service.dto.UserAdminResponseDto;
import com.marketplace.user_service.enums.Status;
import com.marketplace.user_service.service.UserService;
import com.marketplace.user_service.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }


    @GetMapping("/test")
    public String test(){
        return "Working - user service";
    }



    @GetMapping("/{userId}")
    public ResponseEntity<InternalUserDto> getUserById(@PathVariable Long userId ){
       InternalUserDto internalUserDto=  userService.getUserById(userId);
       return ResponseEntity.ok(internalUserDto);
    }

    @PostMapping
    public ResponseEntity<UserAdminResponseDto> createUser(@RequestBody UserAdminCreateDto userAdminCreateDto){
        UserAdminResponseDto user = userService.createUser(userAdminCreateDto);
        URI location = URI.create("/users"+user.id());
        return  ResponseEntity.created(location).body(user);
    }

    @GetMapping("/status/{status}")
    public  ResponseEntity<List<UserAdminResponseDto> >getUserByStatus(@PathVariable Status status){
       List<UserAdminResponseDto> userAdminResponseDto= userService.getUserByStatus(status);
       return ResponseEntity.ok(userAdminResponseDto);

    }



}
