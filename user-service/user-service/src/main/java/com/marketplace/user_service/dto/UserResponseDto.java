package com.marketplace.user_service.dto;

import com.marketplace.user_service.enums.Status;
import jakarta.validation.constraints.NotNull;

public record UserResponseDto (

        @NotNull
        Long id,
        @NotNull
        String email,
        @NotNull
        String name,
        Status status

){
}
