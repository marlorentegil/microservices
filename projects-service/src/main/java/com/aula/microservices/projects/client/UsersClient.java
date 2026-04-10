package com.aula.microservices.projects.client;

import com.aula.microservices.projects.dto.RemoteApiResponse;
import com.aula.microservices.projects.dto.RemoteUserResponse;
import org.springframework.beans.factory.annotation.Qualifier; // Import añadido
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UsersClient {

    private final RestClient usersRestClient;

    public UsersClient(@Qualifier("usersRestClient") RestClient usersRestClient) {
        this.usersRestClient = usersRestClient;
    }

    public RemoteUserResponse findUserById(String userId) {
        RemoteApiResponse<RemoteUserResponse> response = usersRestClient.get()
                .uri("/api/users/{id}", userId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || !response.success() || response.data() == null) {
            throw new IllegalArgumentException("No se pudo recuperar el usuario con id " + userId);
        }

        return response.data();
    }
}