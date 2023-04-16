package com.tunewalker.rest.config;

import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.AdminUser;
import com.tunewalker.rest.repository.AdminUserRepository;
import com.tunewalker.rest.service.impl.JwtService;
import com.tunewalker.rest.util.Decode;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    AdminUserRepository adminUserRepository;

    @Autowired
    JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth") || !request.getServletPath().contains("/admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = getJwtFromRequest(request);

        try{
            if(StringUtils.hasText(jwt)){
                String username = jwtService.extractUsername(jwt);
                UserDetails user = adminUserRepository.findAdminUserByUsername(username).orElseThrow(() -> new TunewalkerException("user not found"));
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (ExpiredJwtException e) {
            //
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return bearerToken;
    }

}
