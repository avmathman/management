package com.invent.management.domain.user;

import com.invent.management.data.user.UserEntity;
import com.invent.management.domain.ModelMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelMapper extends ModelMapper<UserModel, UserEntity> {}
