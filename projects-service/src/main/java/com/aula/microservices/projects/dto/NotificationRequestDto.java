package com.aula.microservices.projects.dto;

public record NotificationRequestDto(
        String projectId,
        String ownerId,
        String message
) {
}