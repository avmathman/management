package com.invent.management.domain.role;

import java.util.List;

/**
 * Provides methods for working with roles.
 */
public interface RoleService {
    /**
     * Creates new role.
     *
     * @param roleModel - The role to be created in database.
     * @return New role created in database.
     */
    RoleModel createRole(RoleModel roleModel);

    /**
     * Updates the specified role and returns updated model.
     *
     * @param roleModel - The role to be update in database.
     * @return Updated role model.
     */
    RoleModel updateRole(RoleModel roleModel);

    /**
     * Removes role with given ID.
     *
     * @param roleId - The role ID.
     */
    void deleteRole(Long roleId);

    /**
     * Returns role by it's ID if it exists.
     *
     * @param roleId - ID of the role to get.
     * @return The role specified by ID or null if no roles found.
     */
    RoleModel getRole(Long roleId);

    /**
     * Returns all roles.
     *
     * @return The list of roles.
     */
    List<RoleModel> getAllRoles();
}
