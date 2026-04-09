package com.aula.microservices.projects.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Project() {
    }

    public Project(String name, String description, String status, String ownerId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}