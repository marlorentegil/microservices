package com.aula.microservices.projects.dto;

import java.time.LocalDateTime;

public record ProjectDetailsResponse(
        Long id,
        String name,
        String description,
        String status,
        Long ownerId,
        String ownerName,
        String ownerEmail,
        LocalDateTime createdAt
) {
}