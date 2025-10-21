package com.pikolic.meli.dto.client;

public record ClientResponseDTO(
   Long id,
   String name,
   Integer age,
   String email,
   String address
) {}
