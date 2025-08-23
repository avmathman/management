package com.invent.management.domain.role;

import java.util.List;
import java.util.Set;

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
     * @param current - List of roles
     * @param existing - List of {@link RoleModel}
     * @throws DuplicateItemException if role with the same name already exists in database.
     */
    void checkValidatity(List<String> current, List<RoleModel> existing);

    /**
     * Finds roles by names.
     *
     * @param names - List of names.
     * @return The list of roles.
     */
    List<RoleModel> findByNames(List<String> names);
}
