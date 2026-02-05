package com.lms.user_service.application.service;

import com.lms.user_service.domain.event.UserCreatedEvent;
import com.lms.user_service.domain.event.UserStatusChangedEvent;
import com.lms.user_service.domain.exception.EmailAlreadyExistsException;
import com.lms.user_service.domain.exception.UserNotFoundException;
import com.lms.user_service.domain.model.User;
import com.lms.user_service.domain.ports.UserEventPublisher;
import com.lms.user_service.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCommandService {
    private final UserRepository repository;
    private final UserEventPublisher eventPublisher;

    public UserCommandService(UserRepository repository, UserEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public User createUser(String fullName, String email) {
        repository.findByEmail(email).ifPresent(user -> {
            throw new EmailAlreadyExistsException(email);
        });

        User saved = repository.save(User.create(fullName, email));
        eventPublisher.publish(new UserCreatedEvent(saved.id().toString(), saved.fullName(), saved.email()));
        return saved;
    }

    @Transactional
    public User changeStatus(Long userId, User.UserStatus status) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        User updated = repository.save(user.withStatus(status));
        eventPublisher.publish(new UserStatusChangedEvent(updated.id().toString(), updated.status()));
        return updated;
    }
}
