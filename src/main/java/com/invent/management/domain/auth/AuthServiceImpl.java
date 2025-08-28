package com.invent.management.domain.auth;

import com.invent.management.configuration.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

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
        String token = "Bearer " + jwtUtil.generateToken(userDetails.getUsername());

        return token;
    }

    @Override
    public boolean signout(String token) {
        return false;
    }
}
