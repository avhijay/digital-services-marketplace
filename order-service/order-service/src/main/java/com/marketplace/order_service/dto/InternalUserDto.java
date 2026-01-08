package com.marketplace.order_service.dto;

import jakarta.validation.constraints.NotNull;

public record InternalUserDto(@NotNull
                              Long id,
                              @NotNull
                              String email,
                              @NotNull
                              String name,

                              @NotNull
                              Status status
) {

    public enum Status {

        ACTIVE,
        INACTIVE,
        DELETED,
        SUSPENDED
    }



}
