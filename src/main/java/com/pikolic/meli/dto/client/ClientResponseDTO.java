package com.pikolic.meli.dto.client;

public record ClientResponseDTO(
   long id,
   String name,
   int age,
   String email,
   String address
) {}
