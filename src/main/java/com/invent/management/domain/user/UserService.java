package com.invent.management.domain.user;

import java.util.List;

import com.invent.management.domain.exception.ItemNotFoundException;

/**
 * Provides methods for working with users.
 */
public interface UserService {

    /**
     * Creates new user.
     *
     * @param userModel - The user to be created in database.
     * @return New user created in database.
     */
    UserModel createUser(UserModel userModel);

    /**
     * Updates the specified user and returns updated model.
     *
     * @param userModel - The user to be update in database.
     * @return Updated user model.
     * @throws ItemNotFoundException if user with the given id does not exist in database.
     */
    UserModel updateUser(UserModel userModel);

    /**
     * Removes user with given ID.
     *
     * @param userId - The user ID.
     */
    void deleteUser(Long userId);

    /**
     * Returns user by it's ID if it exists.
     *
     * @param userId - ID of the user to get.
     * @return The user specified by ID or null if no users found.
     * @throws ItemNotFoundException if user with the given id does not exist in database.
     */
    UserModel getUser(Long userId);

    /**
     * Returns all users.
     *
     * @return The list of users.
     * @throws ItemNotFoundException if user with the given id does not exist in database.
     */
    List<UserModel> getAllUsers();
}
