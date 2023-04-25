package com.tunewalker.rest.service;

import com.tunewalker.rest.model.Subscriber;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface SubscriberService {
    void save(Subscriber subscriber) throws NoSuchAlgorithmException;

    void verifyReCaptcha(String reCaptcha);

    void unSubscribe(String email, String hash);
}
