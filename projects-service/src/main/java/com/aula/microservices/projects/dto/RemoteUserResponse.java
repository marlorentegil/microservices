package com.aula.microservices.projects.dto;

import java.time.LocalDateTime;

public record RemoteUserResponse(
        String id,
        String fullName,
        String email,
        String department,
        Boolean active,
        LocalDateTime createdAt
) {
}