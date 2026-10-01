package com.healthmind.user.mapper;

import com.healthmind.user.dto.AuthResponse;
import com.healthmind.user.dto.RegisterRequest;
import com.healthmind.user.entity.Role;
import com.healthmind.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    public User toEntity(RegisterRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(String.valueOf(Role.PATIENT))
                .build();
    }

    public AuthResponse toAuthResponse(User user) {
        return AuthResponse.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .tokenType("Bearer")
                .accessToken("JWT_COMING_SOON")
                .build();
    }
}
