package com.lms.user_service.infrastructure.web.controller;

import com.lms.user_service.application.service.UserCommandService;
import com.lms.user_service.application.service.UserQueryService;
import com.lms.user_service.infrastructure.web.dto.CreateUserRequest;
import com.lms.user_service.infrastructure.web.dto.UserResponse;
import com.lms.user_service.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        User user = userCommandService.createUser(request.fullName(), request.email());
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        User user = userQueryService.getUserById(id);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        Iterable<User> users = userQueryService.getAllUsers();
        return ResponseEntity.ok(UserResponse.fromDomain(users));
    }

    @GetMapping(params = "email")
    public ResponseEntity<UserResponse> getByEmail(@RequestParam String email) {
        User user = userQueryService.getUserByEmail(email);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }
}
