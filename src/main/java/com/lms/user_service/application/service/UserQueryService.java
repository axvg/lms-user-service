package com.lms.user_service.application.service;

import com.lms.user_service.domain.exception.UserNotFoundException;
import com.lms.user_service.domain.model.User;
import com.lms.user_service.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserQueryService {
    private final UserRepository repository;

    public UserQueryService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
