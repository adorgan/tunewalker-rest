package com.tunewalker.rest.controller;

import com.tunewalker.rest.dto.SubscriberDTO;
import com.tunewalker.rest.exceptions.ReCaptchaException;
import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.Subscriber;
import com.tunewalker.rest.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class SubscribeController {

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping(value = "/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriberDTO subscriberDTO){
        try{
            subscriberService.verifyReCaptcha(subscriberDTO.getReCaptcha());
            subscriberService.save(new Subscriber(subscriberDTO.getEmail()));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Email successfully added");
        }
        catch (TunewalkerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ReCaptchaException | NoSuchAlgorithmException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestParam("email") String email, @RequestParam("hash") String hash){
        try{
            subscriberService.unSubscribe(email, hash);
            return ResponseEntity.status(HttpStatus.OK).body("email successfully deleted");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
