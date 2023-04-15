package com.tunewalker.rest.service.impl;

import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.RefreshToken;
import com.tunewalker.rest.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedAt(new Date());
        return refreshTokenRepository.save(refreshToken);

    }

    public void verifyRefreshToken(RefreshToken token) {
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByToken(token.getToken())
                .orElseThrow(() -> new TunewalkerException("Token Not Found"));
    }
}
