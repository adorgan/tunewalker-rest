package com.tunewalker.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunewalker.rest.service.impl.AuthServiceImpl;

public class AuthenticationResponse {

    @JsonProperty("token")
    private String token;
    @JsonProperty("refreshToken")
    private String refreshToken;

    private AuthenticationResponse(AuthenticationResponseBuilder builder){
        this.token = builder.token;
        this.refreshToken = builder.refreshToken.getToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static class AuthenticationResponseBuilder{
        private String token;
        private RefreshToken refreshToken;

        public AuthenticationResponseBuilder token(String token){
            this.token = token;
            return this;
        }
        public AuthenticationResponseBuilder refreshToken(RefreshToken refreshToken){
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthenticationResponse build(){
            return new AuthenticationResponse(this);
        }

    }
}
