package com.invent.management.api.controllers.user.dto;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.role.RoleModel;
import com.invent.management.domain.user.UserModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting UserReadDto to UserModel.
 */
@Mapper(componentModel = "spring")
public interface UserReadDtoMapper extends DtoMapper<UserReadDto, UserModel> {

    /**
     * Converts entity to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserModel dtoToModel(UserReadDto dto);

    /**
     * Converts model to entity.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    default UserReadDto modelToDto(UserModel model) {
        if (model == null) {
            return null;
        }

        List<String> roles = model.getRoles()
                .stream()
                .map(RoleModel::getName)
                .collect(Collectors.toList());

        final UserReadDto userReadDto = new UserReadDto();
        userReadDto.setId(model.getId());
        userReadDto.setCreatedAt(model.getCreatedAt());
        userReadDto.setModifiedAt(model.getModifiedAt());
        userReadDto.setFirstname(model.getFirstname());
        userReadDto.setLastname(model.getLastname());
        userReadDto.setEmail(model.getEmail());
        userReadDto.setEmail(model.getEmail());
        userReadDto.setEnabled(model.isEnabled());
        userReadDto.setRoles(roles);

        return userReadDto;
    }
}
