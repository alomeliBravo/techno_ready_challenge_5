package com.pikolic.meli.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record OrderUpdateDTO(
        @Positive(message = "clientId must be positive")
        Long clientId,
        @Positive(message = "itemId must be positive")
        Long itemId,
        LocalDate purchaseDate,
        @DecimalMin(value = "0.01", message = "total must be greater than zero")
        Double total
) {}
