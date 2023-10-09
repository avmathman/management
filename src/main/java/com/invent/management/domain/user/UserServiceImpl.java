package com.invent.management.domain.user;

import com.invent.management.data.user.UserEntity;
import com.invent.management.data.user.UserRepository;

import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * {@link UserService} implementation.
 * Implements business logic for working with user models.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserModelMapper userModelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(
            UserRepository repository,
            UserModelMapper userModelMapper,
            RoleService roleService
    ) {
        this.repository = repository;
        this.userModelMapper = userModelMapper;
        this.roleService = roleService;
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        this.roleService.checkMissingRoles(userModel.getRoles());

        try {
            return this.userModelMapper.entityToModel(
                    this.repository.save(this.userModelMapper.modelToEntity(userModel)));
        } catch(DataIntegrityViolationException e) {
            log.error("Failed to save user with user email: {} due to {}", userModel.getEmail(), e.getMessage(), e);
            throw new DataIntegrityViolationException("Given email to create already exist. email = " + userModel.getEmail());
        }
    }

    @Override
    public UserModel updateUser(UserModel user) {
        this.roleService.checkMissingRoles(user.getRoles());

        UserEntity current = this.repository
                .findById(user.getId())
                .orElseThrow(() -> new ItemNotFoundException("User with given id=" + user.getId() + " does not exist!"));

        current.setFirstname(user.getFirstname());
        current.setLastname(user.getLastname());
        current.setEmail(user.getEmail());
        current.setEnabled(user.isEnabled());
        current.setPassword(user.getPassword());

        try{
            return this.userModelMapper.entityToModel(this.repository.save(current));
        } catch(DataIntegrityViolationException e) {
            log.error("Failed to update user with user email: {} due to {}", user.getEmail(), e.getMessage(), e);
            throw new DataIntegrityViolationException("Given email to update already exist. email = " + user.getEmail());
        }

    }

    @Override
    public void deleteUser(Long userId) {
        this.repository
                .findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("User with given id=" + userId + " does not exist!"));

        this.repository.deleteById(userId);
    }

    @Override
    public UserModel getUser(Long userId) {
        return this.userModelMapper.entityToModel(
                this.repository.findById(userId)
                        .orElseThrow(() -> new ItemNotFoundException("User with given id=" + userId + " does not exist!"))
        );
    }

    @Override
    public List<UserModel> getAllUsers() {
        return this.userModelMapper.entitiesToModels(this.repository.findAll());
    }
}
