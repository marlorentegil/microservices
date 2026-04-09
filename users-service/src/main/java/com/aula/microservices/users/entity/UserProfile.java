package com.aula.microservices.users.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_profiles")
public class UserProfile {

    @Id
    private String id;

    private String fullName;

    private String email;

    private String department;

    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    public UserProfile() {
    }

    public UserProfile(String fullName, String email, String department, Boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.active = active;
        this.createdAt = LocalDateTime.now();
    }

    // Getters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public Boolean getActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setActive(Boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}