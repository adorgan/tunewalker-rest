package com.tunewalker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunewalker.rest.model.RefreshToken;

public class AuthenticationResponse {

    @JsonProperty("token")
    private String token;
    @JsonProperty("refreshToken")
    private String refreshToken;

    @JsonProperty("username")
    private String username;

    private AuthenticationResponse(AuthenticationResponseBuilder builder){
        this.token = builder.token;
        this.refreshToken = builder.refreshToken.getToken();
        this.username = builder.username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class AuthenticationResponseBuilder{
        private String token;
        private RefreshToken refreshToken;
        private String username;

        public AuthenticationResponseBuilder token(String token){
            this.token = token;
            return this;
        }
        public AuthenticationResponseBuilder refreshToken(RefreshToken refreshToken){
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthenticationResponseBuilder username(String username){
            this.username = username;
            return this;
        }

        public AuthenticationResponse build(){
            return new AuthenticationResponse(this);
        }

    }
}
