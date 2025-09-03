package com.invent.management.api.controllers.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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
    @ApiModelProperty(value = "The user's email", example = "john.doe@sample.com", required = true)
    @NotNull(message = "User email must be set")
    String email;

    /**
     * The user password.
     */
    @ApiModelProperty(value = "The user's password", example = "password", required = true)
    @NotNull(message = "User password must be set")
    String password;
}
