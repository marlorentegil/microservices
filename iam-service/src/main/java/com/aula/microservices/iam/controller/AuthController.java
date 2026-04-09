package com.aula.microservices.iam.controller;

import com.aula.microservices.common.response.ApiResponse;
import com.aula.microservices.iam.dto.IamUserResponse;
import com.aula.microservices.iam.dto.RegisterUserRequest;
import com.aula.microservices.iam.service.IamUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IamUserService service;

    public AuthController(IamUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<IamUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        return new ApiResponse<>(true, "Usuario registrado correctamente", service.register(request));
    }
}