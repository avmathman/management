package com.invent.management.utils;

import com.invent.management.api.controllers.user.dto.UserCreateDto;
import com.invent.management.domain.user.UserModel;

import java.util.List;

public class UserUtils {
    public UserCreateDto createDefaultUserCreateDto() {
        UserCreateDto dto = this.createDefaultUserCreateDto(null);
        return dto;
    }

    public UserCreateDto createDefaultUserCreateDto(List<String> roles) {
        UserCreateDto dto = new UserCreateDto();

        dto.setFirstname("John");
        dto.setLastname("Doe");
        dto.setEmail("john.doe@test.com");
        dto.setEnabled(true);
        dto.setRoles(roles);

        return dto;
    }

    public UserModel createDefaultUserModel(List<String> roles) {
        UserModel model = new UserModel();

        model.setId(1L);
        model.setFirstname("John");
        model.setLastname("Doe");
        model.setEmail("john.doe@test.com");
        model.setEnabled(true);
        model.setRoles(roles);

        return model;
    }
}
