package com.lms.user_service.domain.model;

import java.time.Instant;

public record User(
        Long id,
        String fullName,
        String email,
        UserStatus status,
        Instant createdAt
) {
    public enum UserStatus {
        ACTIVE,
        INACTIVE
    }

    public static User create(String fullName, String email) {
        return new User(null, fullName, email, UserStatus.ACTIVE, Instant.now());
    }

    public User withStatus(UserStatus newStatus) {
        return new User(id, fullName, email, newStatus, createdAt);
    }
}
