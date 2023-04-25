package com.tunewalker.rest.dto;

public class SubscriberDTO {
    private String email;
    private String reCaptcha;

    public SubscriberDTO(){};
    public SubscriberDTO(String email, String reCaptcha){
        this.email = email;
        this.reCaptcha = reCaptcha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReCaptcha() {
        return reCaptcha;
    }

    public void setReCaptcha(String reCaptcha) {
        this.reCaptcha = reCaptcha;
    }
}
