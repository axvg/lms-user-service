package com.lms.user_service.domain.event;

import com.lms.user_service.domain.model.User;

public class UserStatusChangedEvent extends DomainEvent {
    private final String userId;
    private final User.UserStatus newStatus;

    public UserStatusChangedEvent(String userId, User.UserStatus newStatus) {
        super("UserStatusChangedEvent");
        this.userId = userId;
        this.newStatus = newStatus;
    }

    public String getUserId() {
        return userId;
    }

    public User.UserStatus getNewStatus() {
        return newStatus;
    }
}
