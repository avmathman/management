package com.invent.management.api.controllers.user.dto;

import java.security.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for creating user.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    /**
     * The date when the user entry was created.
     */
    private Timestamp createdAt;

    /**
     * The date when the user entry was modified.
     */
    private Timestamp modifiedAt;

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
