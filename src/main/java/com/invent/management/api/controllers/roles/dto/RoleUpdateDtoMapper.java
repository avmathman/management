package com.invent.management.api.controllers.roles.dto;

import org.mapstruct.Mapper;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.role.RoleModel;

/**
 * Mapper for converting UserUpdateDto to UserModel.
 */
@Mapper(componentModel = "spring")
public interface RoleUpdateDtoMapper extends DtoMapper<RoleUpdateDto, RoleModel> {

    /**
     * Converts entity to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    RoleModel dtoToModel(RoleUpdateDto dto);

    /**
     * Converts model to entity.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    RoleUpdateDto modelToDto(RoleModel model);
}