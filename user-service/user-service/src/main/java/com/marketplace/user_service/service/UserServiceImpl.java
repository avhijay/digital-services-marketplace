package com.marketplace.user_service.service;


import com.marketplace.user_service.dto.*;
import com.marketplace.user_service.entity.Users;
import com.marketplace.user_service.enums.Status;
import com.marketplace.user_service.repository.UserRepository;
import com.marketplace.user_service.exception.EmailNotFoundException;
import com.marketplace.user_service.exception.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public  UserServiceImpl(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override
    public InternalUserDto getUserById(Long id) {
        Users users = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No user found with the ID :"+id));
        return mapToInternal(users);
    }

    @Override
    public List<UserAdminResponseDto> getUserByStatus(Status status) {
        List<Users> users = userRepository.findByStatus(status);
        List<UserAdminResponseDto> userAdminResponseDtos;
        userAdminResponseDtos= users.stream().map(this::mapToAdminResponse).toList();
        return userAdminResponseDtos;
    }

    @Override
    public UserAdminResponseDto createUser(UserAdminCreateDto userAdminCreateDto) {
        Users users = new Users();
        users.setUniqueUserId(UUID.randomUUID().toString());
        users.setEmail(userAdminCreateDto.email());
        users.setName(userAdminCreateDto.name());
        users.setStatus(userAdminCreateDto.status());
        Users user = userRepository.save(users);
        return mapToAdminResponse(user);

    }

    @Override
    public UserResponseDto getUserByEmail(String emailId) {
        Users users = userRepository.findByEmail(emailId).orElseThrow(()-> new EmailNotFoundException("No user found with email :"+emailId));
        return mapToUserResponse(users);
    }

    @Override
    public UserResponseDto getUserByUniqueUserId(String uniqueUserId) {
        Users users = userRepository.findByUniqueUserId(uniqueUserId);
        return mapToUserResponse(users);

    }


    private Users getUserByIdInternal(Long userId){
     return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No user found with the id : "+userId));
    }


    private InternalUserDto mapToInternal(Users users){
        InternalUserDto internalUserDto;
        internalUserDto = new InternalUserDto(users.getId(), users.getEmail(), users.getName(), users.getStatus());
        return  internalUserDto;
    }

    private UserAdminResponseDto mapToAdminResponse( Users users){
        UserAdminResponseDto userAdminResponseDto;
        userAdminResponseDto = new UserAdminResponseDto(users.getId(), users.getEmail(),users.getName(),users.getUniqueUserId(),users.getStatus());
        return userAdminResponseDto;
    }

    private Users mapToUsersFromAdmin(UserAdminRequestDto userAdminRequestDto){
        Users users;
        users = new Users(userAdminRequestDto.id(), userAdminRequestDto.email(), userAdminRequestDto.name(),userAdminRequestDto.uniqueUserId(), userAdminRequestDto.status());
        return users;
    }

    private UserResponseDto mapToUserResponse(Users users){
        UserResponseDto userResponseDto;
        userResponseDto = new UserResponseDto(users.getId(), users.getEmail(), users.getName(), users.getStatus());
        return userResponseDto;
    }





}
