package com.marketplace.user_service.dto;

import com.marketplace.user_service.enums.Status;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

public record InternalUserDto(
        @NotNull
        Long id,
        @NotNull
        String email,
        @NotNull
        String name,

        @NotNull
        Status status


) {
}
