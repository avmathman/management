package com.invent.management.api.controllers.user.dto;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.user.UserModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for converting UserCreateDto to UserModel.
 */
@Mapper(componentModel = "spring")
public interface UserCreateDtoMapper extends DtoMapper<UserCreateDto, UserModel> {

    /**
     * Converts entity to model.
     *
     * @param dto - The dto object.
     * @return The model object.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    UserModel dtoToModel(UserCreateDto dto);

    /**
     * Converts model to entity.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    UserCreateDto modelToDto(UserModel model);
}
