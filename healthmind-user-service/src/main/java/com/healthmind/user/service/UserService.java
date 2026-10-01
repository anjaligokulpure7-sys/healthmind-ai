package com.healthmind.user.service;

import com.healthmind.user.dto.AuthResponse;
import com.healthmind.user.dto.RegisterRequest;
import com.healthmind.user.dto.ServiceRequest;

public interface UserService {

   AuthResponse register(ServiceRequest<RegisterRequest> request);
}
