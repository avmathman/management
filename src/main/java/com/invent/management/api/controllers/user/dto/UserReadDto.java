package com.invent.management.api.controllers.user.dto;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModelProperty;

/**
 * The DTO with available fields for reading user.
 */
public class UserReadDto {

    /**
     * The user unique identifier.
     */
    @ApiModelProperty(value = "The user unique identifier", example = "1")
    private Long id;

    /**
     * The date when the user entry was created.
     */
    @ApiModelProperty(value = "The timestamp when the user was created", example = "2018-01-01T12:00:00Z")
    private Timestamp createdAt;

    /**
     * The date when the user entry was modified.
     */
    @ApiModelProperty(value = "The timestamp when the user was modified", example = "2018-01-01T12:00:00Z")
    private Timestamp modifiedAt;

    /**
     * The user firstname.
     */
    @ApiModelProperty(value = "The user firstname", example = "John")
    private String firstname;

    /**
     * The user lastname.
     */
    @ApiModelProperty(value = "The user lastname", example = "Doe")
    private String lastname;

    /**
     * The user email address.
     */
    @ApiModelProperty(value = "The user's email address", example = "john.doe@example.com")
    private String email;

    /**
     * Is user enabled in system.
     */
    @ApiModelProperty(value = "The user's indicator whether active or not", example = "true")
    private boolean enabled;
}
