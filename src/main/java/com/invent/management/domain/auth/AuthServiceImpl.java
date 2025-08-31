package com.invent.management.domain.auth;

import com.invent.management.configuration.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    // Instead of ConcurrentHashMap, Redis in memory database should be used.
    // This is for testing purposes
    public static final Map<String,String> BLACKLISTED_TOKENS = new ConcurrentHashMap<>();

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            AuthenticationManager authManager,
            JwtUtil jwtUtil
    ) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public String authenticate(AuthModel authModel) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        BLACKLISTED_TOKENS.computeIfPresent(userDetails.getUsername(), (key, value)-> null);
        return "Bearer " + jwtUtil.generateToken(userDetails.getUsername());
    }

    @Override
    public void signout(String jwt) {
        String token = (jwt != null && jwt.startsWith("Bearer ")) ? jwt.substring(7) : jwt;
        String username = jwtUtil.extractUsername(token);
        BLACKLISTED_TOKENS.put(username, jwt);
    }
}
