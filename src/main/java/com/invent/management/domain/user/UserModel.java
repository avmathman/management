package com.invent.management.domain.user;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The model of the application user with login.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    /**
     * The user identifier.
     */
    private Long id;

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
     * The user login e-mail address.
     */
    private String email;

    /**
     * Is user enabled in system.
     */
    private boolean enabled;

    /**
     * The user password.
     */
    private String password;

    /**
     * The user roles.
     */
    private List<String> roles;
}
