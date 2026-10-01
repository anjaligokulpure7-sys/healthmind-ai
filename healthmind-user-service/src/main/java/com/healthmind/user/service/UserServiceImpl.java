package com.healthmind.user.service;

import com.healthmind.user.dto.AuthResponse;
import com.healthmind.user.dto.RegisterRequest;
import com.healthmind.user.dto.RequestContext;
import com.healthmind.user.dto.ServiceRequest;
import com.healthmind.user.entity.User;
import com.healthmind.user.event.UserEventPublisher;
import com.healthmind.user.mapper.UserMapper;
import com.healthmind.user.repository.UserRepository;
import com.healthmind.user.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidation validationService;
    private final UserEventPublisher eventPublisher;

    @Transactional
    public AuthResponse register(ServiceRequest<RegisterRequest> request) {

        RegisterRequest payload = request.getPayload();
        RequestContext context  =  request.getContext();
        String correlationId = context.getCorrelationId();

        log.info("Registration flow started for email: {}", correlationId, payload.getEmail());

        validationService.validateRegistration(payload, correlationId);
        User user = userMapper.toEntity(payload);
        User savedUser = userRepository.save(user);
        log.info("[{}] User Saved is - id: {}", correlationId, savedUser.getId());
        eventPublisher.publishUserRegisteredEvent(savedUser, correlationId);
        AuthResponse response = userMapper.toAuthResponse(savedUser);
        log.info("[{}] Regsiter flow completed successfully", correlationId );
        return response;
    }
}