package com.aula.microservices.projects.service;

import com.aula.microservices.projects.client.NotificationsClient;
import com.aula.microservices.projects.dto.NotificationRequestDto;
import com.aula.microservices.projects.dto.NotificationResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class NotificationOrchestratorService {

    private final NotificationsClient notificationsClient;

    public NotificationOrchestratorService(NotificationsClient notificationsClient) {
        this.notificationsClient = notificationsClient;
    }

    @Retry(name = "notificationsService", fallbackMethod = "fallbackNotification")
    @CircuitBreaker(name = "notificationsService", fallbackMethod = "fallbackNotification")
    public NotificationResponseDto sendNotification(NotificationRequestDto request) {
        return notificationsClient.sendNotification(request);
    }

    public NotificationResponseDto fallbackNotification(NotificationRequestDto request, Throwable throwable) {
        return new NotificationResponseDto(
                "PENDING",
                "No se pudo enviar la notificación ahora. Se marcará como pendiente. Motivo: " + throwable.getClass().getSimpleName(),
                java.time.LocalDateTime.now()
        );
    }
}