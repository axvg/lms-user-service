package com.lms.user_service.infrastructure.persistence.mapper;

import com.lms.user_service.domain.model.User;
import com.lms.user_service.infrastructure.persistence.entity.UserEntity;

public final class UserEntityMapper {
    private UserEntityMapper() {
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                User.UserStatus.valueOf(entity.getStatus().name()),
                entity.getCreatedAt()
        );
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.id());
        entity.setFullName(user.fullName());
        entity.setEmail(user.email());
        entity.setStatus(UserEntity.Status.valueOf(user.status().name()));
        entity.setCreatedAt(user.createdAt());
        return entity;
    }
}
