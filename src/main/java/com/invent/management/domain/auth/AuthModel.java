package com.invent.management.domain.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The model of the application authorization and authentication.
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthModel {

    /**
     * The user login e-mail address.
     */
    String email;

    /**
     * The user password.
     */
    String password;
}
