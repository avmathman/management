package com.invent.management.api.controllers.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "The user unique identifier", example = "1", required = true)
    @NotNull(message = "User identifier must be set")
    private Long id;

    /**
     * The user firstname.
     */
    @ApiModelProperty(value = "The user firstname", example = "John", required = true)
    @NotNull(message = "User firstname must be set")
    @Size(min = 1, max = 256, message = "User firstname length should be between 1 and 256 characters")
    private String firstname;

    /**
     * The user lastname.
     */
    @ApiModelProperty(value = "The user lastname", example = "Doe", readOnly = true)
    @NotNull(message = "User lastname must be set")
    @Size(min = 1, max = 256, message = "User lastname length should be between 1 and 256 characters")
    private String lastname;

    /**
     * The user email address.
     */
    @ApiModelProperty(value = "The user email address", example = "john.doe@example.com", required = true)
    @NotNull(message = "User email must be set")
    @Size(min = 1, max = 256, message = "User email length should be between 1 and 256 characters")
    private String email;

    /**
     * Is user enabled in system.
     */
    @ApiModelProperty(value = "The user's indicator whether active or not", example = "true", required = true)
    private boolean enabled;
}
