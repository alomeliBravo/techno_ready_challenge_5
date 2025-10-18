package com.pikolic.meli.dto.item;

public record ItemResponseDTO(
        long id,
        String name,
        String description,
        double price
) {
}
