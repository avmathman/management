package com.invent.management.api.controllers.roles;

import java.util.List;

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

import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.controllers.roles.dto.RoleCreateDto;
import com.invent.management.api.controllers.roles.dto.RoleCreateDtoMapper;
import com.invent.management.api.controllers.roles.dto.RoleReadDto;
import com.invent.management.api.controllers.roles.dto.RoleReadDtoMapper;
import com.invent.management.api.controllers.roles.dto.RoleUpdateDto;
import com.invent.management.api.controllers.roles.dto.RoleUpdateDtoMapper;
import com.invent.management.domain.role.RoleModel;
import com.invent.management.domain.role.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Roles REST API controller with representing methods.
 */
@Api(tags = {"Role"})
@RestController
@RequestMapping(
        path = "${management.api.prefix:}" + ManagementApiLocations.ROLE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RolesRestController {
    private final RoleService roleService;
    private final RoleCreateDtoMapper roleCreateDtoMapper;
    private final RoleReadDtoMapper roleReadDtoMapper;
    private final RoleUpdateDtoMapper roleUpdateDtoMapper;

    /**
     * Initializes a new {@link RolesRestController} instance.
     *
     * @param roleService - {@link RoleService} instance.
     * @param roleCreateDtoMapper - {@link RoleCreateDtoMapper} instance.
     * @param roleReadDtoMapper - {@link RoleReadDtoMapper} instance.
     * @param roleUpdateDtoMapper - {@link RoleUpdateDtoMapper} instance.
     */
    @Autowired
    public RolesRestController(
            RoleService roleService,
            RoleCreateDtoMapper roleCreateDtoMapper,
            RoleReadDtoMapper roleReadDtoMapper,
            RoleUpdateDtoMapper roleUpdateDtoMapper
    ) {
        this.roleService = roleService;
        this.roleCreateDtoMapper = roleCreateDtoMapper;
        this.roleReadDtoMapper = roleReadDtoMapper;
        this.roleUpdateDtoMapper = roleUpdateDtoMapper;
    }

    /**
     * REST API method to create a new role.
     *
     * @param role - The role to create.
     * @return The created role instance.
     */
    @ApiOperation(value = "Create new role")
    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoleReadDto> createRole(
            @ApiParam(value = "Role in JSON", required = true) @RequestBody RoleCreateDto role) {

        final RoleModel roleModel = this.roleService.createRole(this.roleCreateDtoMapper.dtoToModel(role));
        
        final RoleReadDto createdRole = this.roleReadDtoMapper.modelToDto(this.roleService.createRole(roleModel));

        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    /**
     * REST API method to modify a role.
     *
     * @param role - The role to be modified.
     * @return The modified role instance.
     */
    @ApiOperation(value = "Modify a role")
    @RequestMapping(
            path = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoleReadDto> updateRole(
            @ApiParam(value = "The role JSON", required = true) @RequestBody RoleUpdateDto role) {
        final RoleReadDto updatedRole = this.roleReadDtoMapper
                .modelToDto(this.roleService.updateRole(this.roleUpdateDtoMapper.dtoToModel(role)));

        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    /**
     * REST API method to delete the specified role.
     *
     * @param id - The ID of the role to delete.
     */
    @ApiOperation(value = "Delete role")
    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRole(
            @ApiParam(name = "id", value = "The role identifier", required = true) @PathVariable Long id) {
        this.roleService.deleteRole(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * REST API method to retrieve role by it's ID.
     *
     * @param id - The role ID.
     * @return the role instance.
     */
    @ApiOperation(value = "Get role by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoleReadDto> getRole(
            @ApiParam(name = "id", value = "The role identifier", required = true) @PathVariable Long id) {
        RoleModel role = this.roleService.getRole(id);

        if (role == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final RoleReadDto roleReadDto = this.roleReadDtoMapper.modelToDto(role);
        return new ResponseEntity<>(roleReadDto, HttpStatus.OK);
    }

    /**
     * REST API method to retrieve list of roles.
     *
     * @return The list of role instances.
     */
    @ApiOperation(value = "Get list of roles")
    @RequestMapping(
            path = "/all",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoleReadDto>> getAllRoles() {
        List<RoleReadDto> roles = this.roleReadDtoMapper.modelsToDtos(this.roleService.getAllRoles());

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
