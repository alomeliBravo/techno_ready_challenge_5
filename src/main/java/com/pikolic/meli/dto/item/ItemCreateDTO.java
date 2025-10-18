package com.pikolic.meli.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemCreateDTO(
        @NotBlank String name,
        String description,
        @NotNull @Positive Double price
) {
}
