package com.invent.management.api.controllers.auth;

import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.controllers.auth.dto.AuthDto;
import com.invent.management.api.controllers.auth.dto.AuthDtoMapper;
import com.invent.management.api.controllers.auth.dto.SignOutDto;
import com.invent.management.api.controllers.user.dto.UserReadDto;
import com.invent.management.api.controllers.user.dto.UserReadDtoMapper;
import com.invent.management.domain.auth.AuthModel;
import com.invent.management.domain.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authorization and authentication REST API controller with representing methods.
 */
@Api(tags = {"Authorization and Authentication"})
@RestController
@RequestMapping(
        path = "${management.api.prefix:}" + ManagementApiLocations.AUTH,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthRestController {

    private final AuthService authService;
    private final AuthDtoMapper authDtoMapper;
    private final UserReadDtoMapper userReadDtoMapper;

    public AuthRestController(
        AuthService authService,
        AuthDtoMapper authDtoMapper,
        UserReadDtoMapper userReadDtoMapper
    ) {
        this.authService = authService;
        this.authDtoMapper = authDtoMapper;
        this.userReadDtoMapper = userReadDtoMapper;
    }

    /**
     * REST API method to authorize user.
     *
     * @param authDto - The auth for user.
     * @return The authenricated {@link UserReadDto}.
     */
    @ApiOperation(value = "Authorize user")
    @RequestMapping(
            path = "/sign-in",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> authenticate(
            @ApiParam(value = "Credentials in JSON", required = true) @RequestBody AuthDto authDto
    ) {
        final AuthModel authModel = authDtoMapper.dtoToModel(authDto);
        final String token = authService.authenticate(authModel);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * REST API method to sign out a user.
     *
     * @param dto The sign-out request containing the token.
     * @return HTTP 204 No Content upon successful sign-out.
     */
    @ApiOperation(value = "Sign out user")
    @RequestMapping(
            path = "/sign-out", // Optional: give a meaningful endpoint path
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> signout(
            @ApiParam(value = "Authenticated token in JSON", required = true)
            @RequestBody SignOutDto dto
    ) {
        authService.signout(dto.getToken());

        return ResponseEntity.noContent().build(); // 204 No Content is standard for logout
    }
}
