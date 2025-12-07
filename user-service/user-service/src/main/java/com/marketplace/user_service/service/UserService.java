package com.marketplace.user_service.service;


import com.marketplace.user_service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

        private final Map<Long, UserDto> users = new ConcurrentHashMap<>();
        private final AtomicLong idGeneration = new AtomicLong(1);


        public UserDto createUser(String name , String emailId){
            Long userId = idGeneration.getAndIncrement();
            UserDto newUser = new UserDto(userId,name,emailId);
            users.put(userId, newUser);
            return  newUser;
        }

        public  UserDto getUserById(Long userId){

            if (users.get(userId)==null){
                throw new IllegalArgumentException("No user found ");
            }
            return  users.get(userId);


        }






}
