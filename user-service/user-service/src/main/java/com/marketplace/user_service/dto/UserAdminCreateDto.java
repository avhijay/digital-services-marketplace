package com.marketplace.user_service.dto;

import com.marketplace.user_service.enums.Status;
import jakarta.validation.constraints.NotNull;

public record UserAdminCreateDto(

        @NotNull
        String email,
        @NotNull
        String name,
        @NotNull
        Status status) {
}
