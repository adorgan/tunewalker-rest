package com.tunewalker.rest.controller;

import com.tunewalker.rest.dto.BlogpostDTO;
import com.tunewalker.rest.model.AdminUser;
import com.tunewalker.rest.service.AuthService;
import com.tunewalker.rest.service.BlogpostService;
import com.tunewalker.rest.service.impl.JwtService;
import com.tunewalker.rest.util.ObjectMapperUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AuthService authService;
    @Autowired
    private BlogpostService blogpostService;

    @Autowired
    private JwtService jwtService;

    @GetMapping(value = "/blogpost/{id}")
    public ResponseEntity<?> getAllBlogpostsAdmin(@PathVariable("id") String id, @RequestHeader(value = "Authorization") String authHeader) {

        try {
            if (authHeader == null || !authHeader.contains("Bearer ")) {
                throw new BadCredentialsException("Invalid Credentials");
            }
            jwtService.extractUsername(authHeader.substring(7));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ObjectMapperUtils.map(blogpostService.findByBlogpostId(id), BlogpostDTO.class));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (ExpiredJwtException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

    }
}
