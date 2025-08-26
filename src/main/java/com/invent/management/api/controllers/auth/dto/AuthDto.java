package com.invent.management.api.controllers.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for auth user.
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthDto {

    /**
     * The user login e-mail address.
     */
    String email;

    /**
     * The user password.
     */
    String password;
}
