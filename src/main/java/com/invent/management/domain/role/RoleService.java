package com.invent.management.domain.role;

import java.util.List;

import com.invent.management.domain.exception.DuplicateItemException;
import com.invent.management.domain.exception.ItemNotFoundException;

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
     * @throws ItemNotFoundException if user with the given id does not exist in database.
     */
    RoleModel updateRole(RoleModel roleModel);

    /**
     * Removes role with given ID.
     *
     * @param roleId - The role ID.
     * @throws ItemNotFoundException if user with the given id does not exist in database.
     */
    void deleteRole(Long roleId);

    /**
     * Returns role by it's ID if it exists.
     *
     * @param roleId - ID of the role to get.
     * @return The role specified by ID or null if no roles found.
     * @throws ItemNotFoundException if user with the given id does not exist in database.
     */
    RoleModel getRole(Long roleId);

    /**
     * Returns all roles.
     *
     * @return The list of roles.
     */
    List<RoleModel> getAllRoles();

    /**
     * Checks whether role(s) exist.
     *
     * @param roles - An array of roles
     * @throws DuplicateItemException if role with the same name already exists in database.
     */
    void checkMissingRoles(List<String> roles);

    /**
     * Checks whether role exist.
     *
     * @param role - A role to be checked
     * @throws DuplicateItemException if role with the same name already exists in database.
     */
    void checkRole(String role);
}
