package com.microservice.user_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {
    @Id
    private String id; // trùng với userId từ Keycloak

    private String username;

    private String email;

    private String fullName;

    private String phone;

    private String role; // USER, ADMIN (tùy gán từ Keycloak)

    private boolean active;
}
