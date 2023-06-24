package com.invent.management.api.controllers.user.dto;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.user.UserModel;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUpdateDtoMapper extends DtoMapper<UserUpdateDto, UserModel> {}
