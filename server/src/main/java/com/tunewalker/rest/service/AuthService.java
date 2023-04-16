package com.tunewalker.rest.service;

import com.tunewalker.rest.dto.AuthenticationResponse;
import com.tunewalker.rest.dto.RefreshTokenRequest;
import com.tunewalker.rest.model.LoginRequest;

public interface AuthService {
    AuthenticationResponse login(LoginRequest loginRequest);

    void signup(String authHeader);

    String authenticate(String authHeader);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void logout(RefreshTokenRequest refreshTokenRequest);
}
