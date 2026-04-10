package com.aula.microservices.projects.client;

import com.aula.microservices.projects.dto.NotificationRequestDto;
import com.aula.microservices.projects.dto.NotificationResponseDto;
import com.aula.microservices.projects.dto.RemoteApiResponse;
import org.springframework.beans.factory.annotation.Qualifier; // Importante añadir el import
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationsClient {

    private final RestClient notificationsRestClient;

    public NotificationsClient(@Qualifier("notificationsRestClient") RestClient notificationsRestClient) {
        this.notificationsRestClient = notificationsRestClient;
    }

    public NotificationResponseDto sendNotification(NotificationRequestDto request) {
        RemoteApiResponse<NotificationResponseDto> response = notificationsRestClient.post()
                .uri("/api/notifications")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null) {
            throw new IllegalStateException("No se recibió respuesta válida de notifications-service");
        }

        return response.data();
    }
}