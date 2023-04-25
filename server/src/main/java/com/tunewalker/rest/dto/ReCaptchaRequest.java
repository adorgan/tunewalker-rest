package com.tunewalker.rest.dto;

public class ReCaptchaRequest {
    private String secret;
    private String response;

    public ReCaptchaRequest(String secret, String response) {
        this.secret = secret;
        this.response = response;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ReCaptchaRequest{" +
                "secret='" + secret + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
