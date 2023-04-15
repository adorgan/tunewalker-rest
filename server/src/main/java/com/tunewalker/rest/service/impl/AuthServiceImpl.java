package com.tunewalker.rest.service.impl;

import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.AdminUser;
import com.tunewalker.rest.model.AuthenticationResponse;
import com.tunewalker.rest.model.LoginRequest;
import com.tunewalker.rest.model.RefreshToken;
import com.tunewalker.rest.repository.AdminUserRepository;
import com.tunewalker.rest.service.AuthService;
import com.tunewalker.rest.util.Decode;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

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
}
