package com.invent.management.domain.role;

import com.invent.management.domain.user.UserModel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

//    /**
//     * The role users.
//     */
//    private Set<UserModel> users = new HashSet<>();
}
