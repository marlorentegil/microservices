package com.aula.microservices.users.dto;

import java.time.LocalDateTime;

public record UserResponse(
        String id,
        String fullName,
        String email,
        String department,
        Boolean active,
        LocalDateTime createdAt
) {
}