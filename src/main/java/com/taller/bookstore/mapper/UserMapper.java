package com.taller.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.entity.*;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .build();
    }
}