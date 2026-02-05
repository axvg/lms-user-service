package com.lms.user_service.domain.event;

public class UserCreatedEvent extends DomainEvent {
    private final String userId;
    private final String fullName;
    private final String email;

    public UserCreatedEvent(String userId, String fullName, String email) {
        super("UserCreatedEvent");
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
