package com.invent.management.api.controllers.user.dto;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.role.RoleModel;
import com.invent.management.domain.user.UserModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting UserUpdateDto to UserModel.
 */
@Mapper(componentModel = "spring")
public interface UserUpdateDtoMapper extends DtoMapper<UserUpdateDto, UserModel> {

    /**
     * Converts entity to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserModel dtoToModel(UserUpdateDto dto);

    /**
     * Converts model to entity.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    default UserUpdateDto modelToDto(UserModel model) {
        List<String> roles = model.getRoles()
                .stream()
                .map(RoleModel::getName)
                .collect(Collectors.toList());

        UserUpdateDto dto = new UserUpdateDto();
        dto.setId(model.getId());
        dto.setFirstname(model.getFirstname());
        dto.setLastname(model.getLastname());
        dto.setEmail(model.getEmail());
        dto.setEnabled(model.isEnabled());
        dto.setPassword(model.getPassword());
        dto.setRoles(roles);

        return dto;
    }
}
