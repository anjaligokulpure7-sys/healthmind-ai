package com.healthmind.user.validation;

import com.healthmind.user.dto.RegisterRequest;
import com.healthmind.user.exception.UserAlreadyExistsException;
import com.healthmind.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UserValidation {

    private final UserRepository userRepo;

    public void validateRegistration(RegisterRequest request, String correlationId) {
        log.debug("[{}] Validating registration for Email: {}", correlationId, request.getEmail());

        if(userRepo.existsByEmail(request.getEmail())){
            log.warn("[{}] Validation failed - email exists : {}", correlationId, request.getEmail());
            throw new UserAlreadyExistsException(request.getEmail());
        }
        log.debug("[{}] Validation passed for Email: {}", correlationId, request.getEmail());

    }
}
