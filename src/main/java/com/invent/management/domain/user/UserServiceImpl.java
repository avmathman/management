package com.invent.management.domain.user;

import com.invent.management.data.user.UserEntity;
import com.invent.management.data.user.UserRepository;

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
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserModelMapper userModelMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository repository,
            UserModelMapper userModelMapper
    ) {
        this.repository = repository;
        this.userModelMapper = userModelMapper;
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        try {
            return this.userModelMapper.entityToModel(
                    this.repository.save(this.userModelMapper.modelToEntity(userModel)));
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public UserModel updateUser(UserModel user) {
        UserEntity current = this.repository.getReferenceById(user.getId());

        current.setFirstname(user.getFirstname());
        current.setLastname(user.getLastname());
        current.setEnabled(user.isEnabled());
        current.setPassword(user.getPassword());

        return this.userModelMapper.entityToModel(this.repository.save(current));
    }

    @Override
    public void deleteUser(Long userId) {
        this.repository.deleteById(userId);
    }

    @Override
    public UserModel getUser(Long userId) {
        return this.userModelMapper.entityToModel(this.repository.findById(userId).orElse(null));
    }

    @Override
    public List<UserModel> getAllUsers() {
        return this.userModelMapper.entitiesToModels(this.repository.findAll());
    }
}
