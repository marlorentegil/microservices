package com.aula.microservices.projects.dto;

import java.time.LocalDateTime;

public record ProjectResponse(
                String id,
                String name,
                String description,
                String status,
                String ownerId,
                LocalDateTime createdAt) {
}