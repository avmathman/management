package com.invent.management.api.controllers.roles.dto;

import javax.validation.constraints.NotNull;

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
public class RoleUpdateDto {

    /**
     * The role identifier.
     */
    @ApiModelProperty(value = "The role unique identifier", example = "1", required = true)
    @NotNull(message = "Role identifier must be set")
    private Long id;

    /**
     * The role name.
     */
    @ApiModelProperty(value = "The role name", example = "SAMPLE", required = true)
    @NotNull(message = "Role name must be set")
    private String name;
}
