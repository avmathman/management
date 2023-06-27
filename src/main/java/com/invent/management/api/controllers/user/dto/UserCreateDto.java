package com.invent.management.api.controllers.user.dto;

import io.swagger.annotations.ApiModelProperty;
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
     * The user firstname.
     */
    @ApiModelProperty(value = "The user's firstname", example = "John", required = true)
    private String firstname;

    /**
     * The user lastname.
     */
    @ApiModelProperty(value = "The user's lastname", example = "Doe", required = true)
    private String lastname;

    /**
     * The user email address.
     */
    @ApiModelProperty(value = "The user's email", example = "john.doe@example.com", required = true)
    private String email;

    /**
     * Is user enabled in system.
     */
    @ApiModelProperty(value = "The user's indicator whether active or not", example = "true", required = true)
    private boolean enabled;
}
