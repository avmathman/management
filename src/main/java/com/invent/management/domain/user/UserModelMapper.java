package com.invent.management.domain.user;

import com.invent.management.data.user.UserEntity;
import com.invent.management.domain.ModelMapper;

import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserModelMapper extends ModelMapper<UserModel, UserEntity> {
    default UserEntity modelToEntity(UserModel model){
        if ( model == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( model.getId() );
        userEntity.setCreatedAt( model.getCreatedAt() );
        userEntity.setModifiedAt( model.getModifiedAt() );
        userEntity.setFirstname( model.getFirstname() );
        userEntity.setLastname( model.getLastname() );
        userEntity.setEmail( model.getEmail() );
        userEntity.setPassword( model.getPassword() );
        userEntity.setEnabled( model.isEnabled() );
        userEntity.setRoles( model.getRoles().toString().replaceAll("[|]", "") );

        return userEntity;
    }

    default UserModel entityToModel(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( entity.getId() );
        userModel.setCreatedAt( entity.getCreatedAt() );
        userModel.setModifiedAt( entity.getModifiedAt() );
        userModel.setFirstname( entity.getFirstname() );
        userModel.setLastname( entity.getLastname() );
        userModel.setEmail( entity.getEmail() );
        userModel.setEnabled( entity.isEnabled() );
        userModel.setPassword( entity.getPassword() );

        List<String> roles = Arrays
                .stream(entity.getRoles().split(","))
                .collect(Collectors.toList());
        userModel.setRoles(roles);

        return userModel;
    }
}
