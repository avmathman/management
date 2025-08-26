package com.invent.management.domain.auth;

import com.invent.management.domain.user.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Override
    public UserModel authenticate(AuthModel authModel) {
        return null;
    }

    @Override
    public boolean signout(String token) {
        return false;
    }
}
