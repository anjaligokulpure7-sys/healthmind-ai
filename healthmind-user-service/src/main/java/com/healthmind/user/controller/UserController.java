package com.healthmind.user.controller;


import com.healthmind.user.dto.AuthResponse;
import com.healthmind.user.dto.RegisterRequest;
import com.healthmind.user.dto.RequestContext;
import com.healthmind.user.dto.ServiceRequest;
import com.healthmind.user.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/health")
    public String health() {
        return "Auth service is UP";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request,
                                                 HttpServletRequest httpRequest){
        RequestContext context = RequestContext.builder()
                .requestId(UUID.randomUUID())
                .requestTime(Instant.now())
                .clientIp(httpRequest.getRemoteAddr())
                .userAgent(httpRequest.getHeader("User-Agent"))
                .correlationId(UUID.randomUUID().toString())
                .build();

        ServiceRequest<RegisterRequest> serviceRequest = ServiceRequest.<RegisterRequest>builder()
                .context(context)
                .payload(request)
                .build();

        log.info("Registter request recieved - correlationId: {}, email: {}",
                context.getCorrelationId(),
                request.getEmail());

        AuthResponse response = userService.register(serviceRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }
}
