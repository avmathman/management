package com.invent.management.api.controllers.user.dto;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.user.UserModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    UserModel dtoToModel(UserReadDto dto);

    /**
     * Converts model to entity.
     *
     * @param model - The model object.
     * @return The dto object.
     */
    UserReadDto modelToDto(UserModel model);
}
