package com.taller.bookstore.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taller.bookstore.dto.request.*;
import com.taller.bookstore.dto.response.AuthResponse;
import com.taller.bookstore.entity.User;
import com.taller.bookstore.exception.custom.*;
import com.taller.bookstore.mapper.UserMapper;
import com.taller.bookstore.repository.UserRepository;
import com.taller.bookstore.security.JwtService;
import com.taller.bookstore.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = mapper.toEntity(request);
        user.setPassword(encoder.encode(user.getPassword()));

        repository.save(user);

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(token, 86400000L, user.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(token, 86400000L, user.getRole().name());
    }
}