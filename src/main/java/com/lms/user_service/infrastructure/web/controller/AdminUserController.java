package com.lms.user_service.infrastructure.web.controller;

import com.lms.user_service.application.service.UserCommandService;
import com.lms.user_service.infrastructure.web.dto.UserResponse;
import com.lms.user_service.infrastructure.web.dto.UserStatusRequest;
import com.lms.user_service.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
    private final UserCommandService userCommandService;

    public AdminUserController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UserResponse> changeStatus(
            @PathVariable Long id,
            @Valid @RequestBody UserStatusRequest request) {
        User user = userCommandService.changeStatus(id, request.status());
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }
}
