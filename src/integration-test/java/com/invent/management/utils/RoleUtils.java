package com.invent.management.utils;

import com.invent.management.api.controllers.roles.dto.RoleCreateDto;
import com.invent.management.api.controllers.roles.dto.RoleReadDto;
import com.invent.management.api.controllers.roles.dto.RoleUpdateDto;
import com.invent.management.data.role.RoleEntity;
import com.invent.management.domain.role.RoleModel;

import javax.management.relation.Role;

public class RoleUtils {

    public RoleCreateDto createDefaultRoleCreateDto() {
        RoleCreateDto dto = new RoleCreateDto();
        dto.setName("TEST");

        return dto;
    }

    public RoleUpdateDto createDefaultRoleUpdateDto() {
        RoleUpdateDto dto = new RoleUpdateDto();
        dto.setName("TEST");
        dto.setId(1L);

        return dto;
    }

    public RoleReadDto createDefaultRoleReadDto() {
        RoleReadDto dto = new RoleReadDto();
        dto.setName("TEST");
        dto.setId(1L);

        return dto;
    }

    public RoleEntity createRoleEntity() {
        RoleEntity entity = new RoleEntity();
        entity.setId(1L);
        entity.setName("TEST");

        return entity;
    }

    public RoleModel createRoleModel() {
        RoleModel model = new RoleModel();
        model.setId(1L);
        model.setName("TEST");

        return model;
    }
}
