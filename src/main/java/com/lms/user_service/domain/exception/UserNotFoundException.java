package com.lms.user_service.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super((id == null ? "User not found" : "User with id %d not found".formatted(id)));
    }

    public UserNotFoundException(String email) {
        super((email == null ? "User not found" : "User with email %s not found".formatted(email)));
    }
}
