package com.invent.management.api.controllers.user;

import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.controllers.user.dto.UserCreateDto;
import com.invent.management.api.controllers.user.dto.UserCreateDtoMapper;
import com.invent.management.api.controllers.user.dto.UserReadDto;
import com.invent.management.api.controllers.user.dto.UserReadDtoMapper;
import com.invent.management.api.controllers.user.dto.UserUpdateDto;
import com.invent.management.api.controllers.user.dto.UserUpdateDtoMapper;
import com.invent.management.domain.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReadDto> createUser(@RequestBody UserCreateDto user) {
        final UserReadDto createdUser = this.userReadDtoMapper
                .modelToDto(this.userService.createUser(this.userCreateDtoMapper.dtoToModel(user)));

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadDto> updateUser(@RequestBody UserUpdateDto user) {
        final UserReadDto updatedUser = this.userReadDtoMapper
                .modelToDto(this.userService.updateUser(this.userUpdateDtoMapper.dtoToModel(user)));

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {
        this.userService.deleteUser(Long.parseLong(userId));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            path = "",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserReadDto> getUser(@PathVariable("userId") String userId) {
        Long uId = Long.parseLong(userId);
        final UserReadDto currentUser = this.userReadDtoMapper.modelToDto(this.userService.getUser(uId));

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

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
