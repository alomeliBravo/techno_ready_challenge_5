package com.pikolic.meli.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ItemUpdateDTO(
        @NotBlank String name,
        String description,
        @Positive double price
) {}
