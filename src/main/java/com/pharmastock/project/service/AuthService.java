package com.pharmastock.project.service;

import com.pharmastock.project.dto.AuthRequest;
import com.pharmastock.project.dto.AuthResponse;
import com.pharmastock.project.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
