package com.tunewalker.rest.config;

import com.tunewalker.rest.exceptions.TunewalkerException;
import com.tunewalker.rest.model.AdminUser;
import com.tunewalker.rest.repository.AdminUserRepository;
import com.tunewalker.rest.util.Decode;
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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    AdminUserRepository adminUserRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth") || request.getServletPath().contains("/blogpost")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(request);
//        String decoded = Decode.decodeString(request.getHeader("Authorization"));
//        String username = decoded.substring(0, decoded.indexOf(":"));
//        UserDetails user = adminUserRepository.findAdminUserByUsername(username).orElseThrow(() -> new TunewalkerException("user not found"));
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                user,
//                null,
//                user.getAuthorities()
//        );
//        authToken.setDetails(
//                new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

}
