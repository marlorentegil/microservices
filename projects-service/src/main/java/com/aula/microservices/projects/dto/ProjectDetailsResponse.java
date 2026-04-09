package com.aula.microservices.projects.dto;

import java.time.LocalDateTime;

public record ProjectDetailsResponse(
        String id,
        String name,
        String description,
        String status,
        String ownerId,
        String ownerName,
        String ownerEmail,
        LocalDateTime createdAt
) {
}