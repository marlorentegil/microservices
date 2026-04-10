package com.aula.microservices.projects.dto;

public record ProjectCreationResult(
        ProjectResponse project,
        String notificationStatus,
        String notificationDetail
) {
}