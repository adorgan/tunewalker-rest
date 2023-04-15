package com.tunewalker.rest.service;

import com.tunewalker.rest.dto.AdminUserDTO;
import com.tunewalker.rest.model.AuthenticationResponse;
import com.tunewalker.rest.model.LoginRequest;

public interface AuthService {
    AuthenticationResponse login(LoginRequest loginRequest);

    void signup(String authHeader);

    String authenticate(String authHeader);
}
