package com.invent.management.api.controllers.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
* Passes authenticated user token to sign-out.
*/
@Getter
@Setter
@NoArgsConstructor
public class SignOutDto {

    /**
    * Authenticated user token.
    */
    @ApiModelProperty(value = "The authorized jwt", example = "1234asdf#44$", required = true)
    @NotNull(message = "jwt must be set")
    String jwt;
}
