package com.aula.microservices.projects.dto;

public record RemoteApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}