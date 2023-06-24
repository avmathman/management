package com.invent.management.api.controllers.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for updating user.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {

    /**
     * The user identifier.
     */
    private Long id;

    /**
     * The user firstname.
     */
    private String firstname;

    /**
     * The user lastname.
     */
    private String lastname;

    /**
     * The user e-mail address.
     */
    private String email;

    /**
     * Is user enabled in system.
     */
    private boolean enabled;
}
