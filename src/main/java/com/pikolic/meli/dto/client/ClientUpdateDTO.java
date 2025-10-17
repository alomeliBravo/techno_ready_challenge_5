package com.pikolic.meli.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ClientUpdateDTO(
        @NotBlank(message = "name is required")
        String name,
        @Positive
        int age,
        @NotBlank(message = "email is required")
        String email,
        String address
) {}
