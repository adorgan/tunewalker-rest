package com.tunewalker.rest.service.impl;

import com.tunewalker.rest.dto.RefreshTokenRequest;
import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.AdminUser;
import com.tunewalker.rest.dto.AuthenticationResponse;
import com.tunewalker.rest.model.LoginRequest;
import com.tunewalker.rest.model.RefreshToken;
import com.tunewalker.rest.repository.AdminUserRepository;
import com.tunewalker.rest.repository.RefreshTokenRepository;
import com.tunewalker.rest.service.AuthService;
import com.tunewalker.rest.util.Decode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AdminUserRepository adminUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;


    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            UserDetails user = adminUserRepository.findAdminUserByUsername(username).orElseThrow(() -> new TunewalkerException("user not found"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user,
                            password
                    )
            );
            String jwt = jwtService.generateToken(user);
            RefreshToken refreshToken =refreshTokenService.generateRefreshToken();

            return new AuthenticationResponse.AuthenticationResponseBuilder()
                    .token(jwt)
                    .refreshToken(refreshToken)
                    .username(username)
                    .build();
        } catch (Exception e) {
            throw new TunewalkerException(e.getMessage());
        }
    }

    @Override
    public void signup(String authHeader) {
        try{
            AdminUser user = new AdminUser();
            String decoded = Decode.decodeString(authHeader);
            String username = decoded.substring(0, decoded.indexOf(":"));
            String password = decoded.substring(decoded.indexOf(":") + 1);
            String hashedPw = passwordEncoder.encode(password);
            user.setUsername(username);
            user.setPassword(hashedPw);
            adminUserRepository.save(user);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String authenticate(String header) {

        try{
            String decoded = Decode.decodeString(header);
            String username = decoded.substring(0, decoded.indexOf(":"));
            String password = decoded.substring(decoded.indexOf(":") + 1);
            UserDetails user = adminUserRepository.findAdminUserByUsername(username).orElseThrow(() -> new TunewalkerException("user not found"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user,
                            password
                    )
            );

            return jwtService.generateToken(user);

        } catch (AuthenticationException | UnsupportedEncodingException e) {
            throw new TunewalkerException(e.getMessage());
        }

    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByToken(refreshTokenRequest.getToken())
                .orElseThrow(() -> new TunewalkerException("No Refresh Token Found"));
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(refreshTokenRequest.getUsername());
        String jwt = jwtService.generateToken(adminUser);
        return new AuthenticationResponse.AuthenticationResponseBuilder()
                .token(jwt)
                .refreshToken(refreshToken)
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    @Override
    public void logout(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenRepository.deleteByToken(refreshTokenRequest.getToken());
    }

    private String getJwtFromHeader(String request) {

        if(StringUtils.hasText(request) && request.startsWith("Bearer ")){
            return request.substring(7);
        }
        return request;
    }
}
