package com.invent.management.api.controllers.user;

import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.controllers.user.dto.UserCreateDto;
import com.invent.management.api.controllers.user.dto.UserCreateDtoMapper;
import com.invent.management.api.controllers.user.dto.UserReadDto;
import com.invent.management.api.controllers.user.dto.UserReadDtoMapper;
import com.invent.management.api.controllers.user.dto.UserUpdateDto;
import com.invent.management.api.controllers.user.dto.UserUpdateDtoMapper;
import com.invent.management.domain.user.UserModel;
import com.invent.management.domain.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Users REST API controller with representing methods.
 */
@Api(tags = {"User"})
@RestController
@RequestMapping(
        path = "${management.api.prefix:}" + ManagementApiLocations.USER,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UsersRestController {
    private final UserService userService;
    private final UserCreateDtoMapper userCreateDtoMapper;
    private final UserReadDtoMapper userReadDtoMapper;
    private final UserUpdateDtoMapper userUpdateDtoMapper;

    /**
     * Initializes a new {@link UsersRestController} instance.
     *
     * @param userService - {@link UserService} instance.
     * @param userCreateDtoMapper - {@link UserCreateDtoMapper} instance.
     * @param userReadDtoMapper - {@link UserReadDtoMapper} instance.
     * @param userUpdateDtoMapper - {@link UserUpdateDtoMapper} instance.
     */
    @Autowired
    public UsersRestController(
            UserService userService,
            UserCreateDtoMapper userCreateDtoMapper,
            UserReadDtoMapper userReadDtoMapper,
            UserUpdateDtoMapper userUpdateDtoMapper
    ) {
        this.userService = userService;
        this.userCreateDtoMapper = userCreateDtoMapper;
        this.userReadDtoMapper = userReadDtoMapper;
        this.userUpdateDtoMapper = userUpdateDtoMapper;
    }

    /**
     * REST API method to create a new user.
     *
     * @param user - The user to create.
     * @return The created user instance.
     */
    @ApiOperation(value = "Create new user")
    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReadDto> createUser(
            @ApiParam(value = "User in JSON", required = true) @RequestBody UserCreateDto user) {

        final UserModel userModel = this.userService.createUser(this.userCreateDtoMapper.dtoToModel(user));
        
        final UserReadDto createdUser = this.userReadDtoMapper.modelToDto(this.userService.createUser(userModel));

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * REST API method to modify a user.
     *
     * @param user - The user to be modified.
     * @return The modified user instance.
     */
    @ApiOperation(value = "Modify a user")
    @RequestMapping(
            path = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadDto> updateUser(
            @ApiParam(value = "The user JSON", required = true) @RequestBody UserUpdateDto user) {
        final UserReadDto updatedUser = this.userReadDtoMapper
                .modelToDto(this.userService.updateUser(this.userUpdateDtoMapper.dtoToModel(user)));

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * REST API method to delete the specified user.
     *
     * @param id The ID of the user to delete.
     */
    @ApiOperation(value = "Delete user")
    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(
            @ApiParam(name = "id", value = "The user identifier", required = true) @PathVariable Long id) {
        this.userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * REST API method to retrieve user by it's ID.
     *
     * @param id - The user ID.
     * @return the user instance.
     */
    @ApiOperation(value = "Get user by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadDto> getUser(
            @ApiParam(name = "id", value = "The user identifier", required = true) @PathVariable Long id) {
        UserModel user = this.userService.getUser(id);

        if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final UserReadDto userDto = this.userReadDtoMapper.modelToDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * REST API method to retrieve list of users.
     *
     * @return The list of user instances.
     */
    @ApiOperation(value = "Get list of users")
    @RequestMapping(
            path = "/all",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserReadDto>> getAllUsers() {
        List<UserReadDto> users = this.userReadDtoMapper.modelsToDtos(this.userService.getAllUsers());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
