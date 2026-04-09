package com.aula.microservices.iam.service;

import com.aula.microservices.iam.dto.IamUserResponse;
import com.aula.microservices.iam.dto.RegisterUserRequest;
import com.aula.microservices.iam.entity.IamUser;
import com.aula.microservices.iam.repository.IamUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IamUserService {

    private final IamUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public IamUserService(IamUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public IamUserResponse register(RegisterUserRequest request) {
        if (repository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("El username ya existe");
        }

        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("El email ya existe");
        }

        IamUser user = new IamUser(
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                "ROLE_USER",
                true
        );

        IamUser saved = repository.save(user);

        return new IamUserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getRoles(),
                saved.getEnabled(),
                saved.getCreatedAt()
        );
    }
}