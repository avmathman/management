package com.invent.management.domain.role;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * The model of the application role for a user.
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleModel {

    /**
     * The role identifier.
     */
    private Long id;

    /**
     * The role name.
     */
    private String name;
}
