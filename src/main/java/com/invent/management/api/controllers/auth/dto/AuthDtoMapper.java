package com.invent.management.api.controllers.auth.dto;

import com.invent.management.api.controllers.dto.DtoMapper;
import com.invent.management.domain.auth.AuthModel;
import org.mapstruct.Mapper;

/**
 * Mapper for converting AuthDto to AuthModel.
 */
@Mapper(componentModel = "spring")
public interface AuthDtoMapper  extends DtoMapper<AuthDto, AuthModel>  {
}
