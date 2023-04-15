package com.tunewalker.rest.controller;

import com.tunewalker.rest.dto.AdminUserDTO;
import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.AuthenticationResponse;
import com.tunewalker.rest.model.LoginRequest;
import com.tunewalker.rest.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
        }
        catch (TunewalkerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestHeader(value = "Authorization") String authHeader){
        try{
            authService.signup(authHeader);
            return new ResponseEntity<>("Acco", HttpStatus.OK);
        }
        catch (TunewalkerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestHeader(value = "Authorization") String authHeader
    ) {
        try{
            return new ResponseEntity<>(authService.authenticate(authHeader), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }
}
