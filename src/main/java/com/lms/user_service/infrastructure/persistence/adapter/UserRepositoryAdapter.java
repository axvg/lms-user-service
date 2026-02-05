package com.lms.user_service.infrastructure.persistence.adapter;

import com.lms.user_service.domain.model.User;
import com.lms.user_service.domain.repository.UserRepository;
import com.lms.user_service.infrastructure.persistence.entity.UserEntity;
import com.lms.user_service.infrastructure.persistence.mapper.UserEntityMapper;
import com.lms.user_service.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {
    private final SpringDataUserRepository repository;

    public UserRepositoryAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntityMapper.toEntity(user);
        UserEntity saved = repository.save(entity);
        return UserEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(UserEntityMapper::toDomain);
    }
}
