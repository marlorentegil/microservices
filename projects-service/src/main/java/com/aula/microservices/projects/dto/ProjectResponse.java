package com.aula.microservices.projects.dto;

import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        String status,
        Long ownerId,
        LocalDateTime createdAt
) {
}