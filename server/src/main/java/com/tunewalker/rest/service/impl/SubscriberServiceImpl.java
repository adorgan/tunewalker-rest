package com.tunewalker.rest.service.impl;

import com.sendgrid.helpers.mail.objects.Email;
import com.tunewalker.rest.dto.ReCaptchaRequest;
import com.tunewalker.rest.dto.ReCaptchaResponse;
import com.tunewalker.rest.exceptions.ReCaptchaException;
import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.MailHelper;
import com.tunewalker.rest.model.Subscriber;
import com.tunewalker.rest.repository.SubscriberRepository;
import com.tunewalker.rest.service.EmailService;
import com.tunewalker.rest.service.SubscriberService;
import com.tunewalker.rest.util.EmailSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    Logger logger = LoggerFactory.getLogger(SubscriberServiceImpl.class);
    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private EmailService emailService;

    @Value("${recaptcha.secret.key}")
    private String secretKey;


    @Override
    public void save(Subscriber subscriber) throws NoSuchAlgorithmException {
        Subscriber s = subscriberRepository.findByEmail(subscriber.getEmail());
        if(s != null){
            throw new TunewalkerException("Email already exists");
        }
        else{

            subscriberRepository.save(subscriber);
            String hash = createHash(subscriber.getEmail(), subscriber.getId());
            MailHelper mailHelper = new MailHelper.Builder()
                    .to(subscriber.getEmail())
                    .from("tim@thetunewalker.com")
                    .subject("Welcome To The Tune Walker")
                    .body(new EmailSubscription(hash, subscriber.getEmail()).getHTML())
                    .build();
            try{
                emailService.sendEmail(mailHelper);
            } catch (IOException | TunewalkerException e ) {
                throw new TunewalkerException(e.getMessage());
            }


        }
    }

    @Override
    public void verifyReCaptcha(String reCaptcha) {
        RestTemplate restTemplate = new RestTemplate();
        ReCaptchaRequest request = new ReCaptchaRequest(secretKey, reCaptcha);
        URI RECAPTCHA_URL = URI.create(String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s",
                secretKey, reCaptcha));
        ReCaptchaResponse reCaptchaResponse = restTemplate.postForObject(RECAPTCHA_URL, request, ReCaptchaResponse.class);
        assert reCaptchaResponse != null;
        if(!reCaptchaResponse.isSuccess()){
            throw new ReCaptchaException("Invalid reCAPTCHA");
        }
    }

    @Override
    public void unSubscribe(String email, String hash) {
        Subscriber subscriber = subscriberRepository.findByEmail(email);
        if(subscriber != null){
            try{
                String subscriberHash = createHash(email, subscriber.getId());
                if(subscriberHash.equals(hash)){
                    subscriberRepository.delete(subscriber);
                }

            }
            catch (NoSuchAlgorithmException e){
                throw new TunewalkerException(e.getMessage());
            }
        }
    }

    private String createHash(String email, String id) throws NoSuchAlgorithmException {
        String combined = email.concat(id).concat(secretKey);
        MessageDigest digest;
        try{
            digest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e){
            throw new TunewalkerException(e.getMessage());
        }

        byte[] hash = digest.digest(
                combined.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
