package com.lms.user_service.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lms.user_service.domain.model.User;

import java.time.Instant;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        User.UserStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Instant createdAt
) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
                user.id(),
                user.fullName(),
                user.email(),
                user.status(),
                user.createdAt()
        );
    }
}
