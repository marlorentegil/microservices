package com.aula.microservices.projects.dto;

public record NotificationRequestDto(
        Long projectId,
        Long ownerId,
        String message
) {
}