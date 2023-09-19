package com.invent.management.api.controllers.roles.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for creating role.
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleCreateDto {
    
    /**
     * The role name.
     */
    @ApiModelProperty(value = "The role name", example = "SAMPLE", required = true)
    @NotNull(message = "Name must be set")
    private String name;
}
