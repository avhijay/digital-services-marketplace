package com.marketplace.user_service.service;


import com.marketplace.user_service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

        private final Map<Long, UserDto> users = new ConcurrentHashMap<>();
        private final AtomicLong idGeneration = new AtomicLong();


        public UserDto createUser(String name , String email){
            Long userId = idGeneration.getAndIncrement();
            UserDto newUser = new UserDto(userId,name,email);
            users.put(userId,newUser);
            return  newUser;
        }

        public  UserDto getUserById(Long id){
            return  users.get(id);
        }






}
