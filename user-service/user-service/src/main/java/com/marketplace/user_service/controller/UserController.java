package com.marketplace.user_service.controller;

import com.marketplace.user_service.dto.CreateUser;
import com.marketplace.user_service.dto.UserDto;
import com.marketplace.user_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


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
        log.info("Request : createUser = received ");

       UserDto users =  userService.createUser(request.getName(),request.getEmailId());
       log.info("Request createUser completed Successfully");

        return ResponseEntity.ok(users);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUser(@PathVariable Long userId){
        log.info("Request : getUserById = received");
//userService.testingCircuitBreaker(2);
        UserDto users = userService.getUserById(userId);
        log.info("Request getUserById completed Successfully");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/test/circuitbreaker/{delay}")
    public ResponseEntity<String>circuitBreakerTest(@PathVariable int delay){
        return ResponseEntity .ok(userService.testingCircuitBreaker(delay));
    }



}
