package com.invent.management.domain.role;

import org.mapstruct.Mapper;

import com.invent.management.data.role.RoleEntity;
import com.invent.management.domain.ModelMapper;

@Mapper(componentModel = "spring")
public interface RoleModelMapper extends ModelMapper<RoleModel, RoleEntity> {}
