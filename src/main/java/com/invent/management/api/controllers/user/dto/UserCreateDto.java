package com.invent.management.api.controllers.user.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @NotNull(message = "User firstname must be set")
    private String firstname;

    /**
     * The user lastname.
     */
    @ApiModelProperty(value = "The user's lastname", example = "Doe", required = true)
    @NotNull(message = "User lastname must be set")
    private String lastname;

    /**
     * The user email address.
     */
    @ApiModelProperty(value = "The user's email", example = "john.doe@example.com", required = true)
    @NotNull(message = "User email must be set")
    private String email;

    /**
     * Is user enabled in system.
     */
    @ApiModelProperty(value = "The user's indicator whether active or not", example = "true", required = true)
    @NotNull(message = "User enabled must be set")
    private boolean enabled;

    /**
     * Is user enabled in system.
     */
    @ApiModelProperty(value = "The user's password", example = "password", required = true)
    @NotNull(message = "User password must be set")
    private String password;

    /**
     * Is user enabled in system.
     */
    @ApiModelProperty(value = "The user's roles", example = "[\"ADMIN\", \"USER\"]", required = true)
    @NotNull(message = "User role(s) must be set")
    private List<String> roles;
}
