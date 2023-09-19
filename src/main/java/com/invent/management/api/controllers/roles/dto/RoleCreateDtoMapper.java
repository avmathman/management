package com.invent.management.api.controllers.roles.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.role.RoleModel;

/**
 * Mapper for converting RoleCreateDto to UserModel.
 */
@Mapper(componentModel = "spring")
public interface RoleCreateDtoMapper extends DtoMapper<RoleCreateDto, RoleModel> {

    /**
     * Converts entity to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    @Mapping(target = "id", ignore = true)
    RoleModel dtoToModel(RoleCreateDto dto);

    /**
     * Converts model to entity.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    RoleCreateDto modelToDto(RoleModel model);
}