package com.invent.management.domain.auth;

import com.invent.management.domain.user.UserModel;

/**
 * Provides methods for working with authorization and authentication.
 */
public interface AuthService {

    /**
     * Authorizes user.
     *
     * @param authModel - The user to be created in database.
     * @return Existing user {@link UserModel}.
     */
    UserModel authenticate(AuthModel authModel);

    /**
     * Signs-out user.
     *
     * @param token - The authenticated user token.
     * @return True if success, False if does not successes.
     */
    boolean signout(String token);
}
