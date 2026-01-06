package com.marketplace.user_service.service;

import com.marketplace.user_service.dto.*;
import com.marketplace.user_service.enums.Status;

import java.util.List;

public interface UserService {


InternalUserDto getUserById(Long id);

List<UserAdminResponseDto> getUserByStatus(Status status);

UserAdminResponseDto createUser(UserAdminCreateDto userAdminCreateDto);

UserResponseDto getUserByEmail(String emailId);

UserResponseDto getUserByUniqueUserId(String uniqueUserId);




}
