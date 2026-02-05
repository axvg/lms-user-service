package com.lms.user_service.infrastructure.web.dto;

import com.lms.user_service.domain.model.User;
import jakarta.validation.constraints.NotNull;

public record UserStatusRequest(@NotNull User.UserStatus status) {
}
