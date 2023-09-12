package com.invent.management.api.controllers.roles.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The DTO with available fields for reading role.
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleReadDto {

    /**
     * The role unique identifier.
     */
    @ApiModelProperty(value = "The role unique identifier", example = "1")
    private Long id;

    /**
     * The role name.
     */
    @ApiModelProperty(value = "The role name", example = "SAMPLE")
    private String name;
}
