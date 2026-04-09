package com.aula.microservices.iam.dto;

import java.time.LocalDateTime;

public record IamUserResponse(
        String id,
        String username,
        String email,
        String roles,
        Boolean enabled,
        LocalDateTime createdAt
) {
}
