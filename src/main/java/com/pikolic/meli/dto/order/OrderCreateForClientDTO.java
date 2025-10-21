package com.pikolic.meli.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderCreateForClientDTO(
        @NotNull(message = "item_id is required")
        @Positive(message = "item_id must be positive")
        Long itemId
) {}
