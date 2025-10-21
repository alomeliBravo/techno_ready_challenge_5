package com.pikolic.meli.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record OrderCreateDTO(
        @NotNull(message = "client_id is required")
        @Positive(message = "client_id must be positive")
        Long clientId,
        @NotNull(message = "item_id is required")
        @Positive(message = "item_id must be positive")
        Long itemId,
        @NotNull(message = "purchaseDate is required")
        LocalDate purchaseDate,
        @NotNull(message = "total is required")
        @DecimalMin(value = "0.01", message = "total must be greater than zero")
        Double total
) {}
