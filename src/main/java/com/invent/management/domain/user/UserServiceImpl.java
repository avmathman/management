package com.invent.management.domain.user;

import com.invent.management.data.user.UserEntity;
import com.invent.management.data.user.UserRepository;

import com.invent.management.domain.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * {@link UserService} implementation.
 * Implements business logic for working with user models.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserModelMapper userModelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository repository,
            UserModelMapper userModelMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.userModelMapper = userModelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        try {
            String hashedPassword = passwordEncoder.encode(userModel.getPassword());
            UserEntity entity = this.userModelMapper.modelToEntity(userModel);
            entity.setPassword(hashedPassword);

            return this.userModelMapper.entityToModel(
                    this.repository.save(entity));
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to save user with user email: {} due to {}", userModel.getEmail(), e.getMessage(), e);
            throw new DataIntegrityViolationException("Given email to create already exist. email = " + userModel.getEmail());
        }
    }

    @Override
    public UserModel updateUser(UserModel user) {
        UserEntity current = this.repository
                .findByIdAndEmail(user.getId(), user.getEmail())
                .orElseThrow(() -> new ItemNotFoundException("User does not exist with given id=" + user.getId() + " and given email=" + user.getEmail()));

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        current.setFirstname(user.getFirstname());
        current.setLastname(user.getLastname());
        current.setEnabled(user.isEnabled());
        current.setPassword(hashedPassword);

        try {
            return this.userModelMapper.entityToModel(this.repository.save(current));
        } catch (DataIntegrityViolationException e) {
            String msg = String.format("Failed to update user with email: %s due to %s", user.getEmail(), e.getCause().getMessage());
            log.error(msg);
            throw new DataIntegrityViolationException(msg);
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

    @PostConstruct
    public void init() {
        log.info("Default user. email: john.doe@sample.com | password: pass");
    }
}
