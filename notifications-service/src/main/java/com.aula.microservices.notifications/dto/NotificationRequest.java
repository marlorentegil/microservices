package com.aula.microservices.notifications.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NotificationRequest(

        @NotNull(message = "El projectId es obligatorio")
        Long projectId,

        @NotNull(message = "El ownerId es obligatorio")
        Long ownerId,

        @NotBlank(message = "El mensaje es obligatorio")
        @Size(max = 300, message = "El mensaje no puede superar 300 caracteres")
        String message
) {
}