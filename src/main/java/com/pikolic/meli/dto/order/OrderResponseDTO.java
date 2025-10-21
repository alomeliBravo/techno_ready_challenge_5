package com.pikolic.meli.dto.order;

import java.time.LocalDate;

public record OrderResponseDTO(
        Long id,
        Long client_id,
        Long item_id,
        LocalDate purchaseDate,
        Double total
) {}
