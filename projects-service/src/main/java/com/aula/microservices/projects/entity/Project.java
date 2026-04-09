package com.aula.microservices.projects.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "projects")
public class Project {

    @Id
    private String id;

    private String name;
    private String description;
    private String status;
    
    @Field("owner_id")
    private Long ownerId;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Project() {}

    public Project(String name, String description, String status, Long ownerId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}