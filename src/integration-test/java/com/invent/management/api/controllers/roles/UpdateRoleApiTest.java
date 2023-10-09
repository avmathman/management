package com.invent.management.api.controllers.roles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.api.controllers.roles.dto.RoleCreateDto;
import com.invent.management.api.controllers.roles.dto.RoleReadDto;
import com.invent.management.api.controllers.roles.dto.RoleUpdateDto;
import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.role.RoleModel;
import com.invent.management.domain.role.RoleService;
import com.invent.management.utils.RoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class UpdateRoleApiTest {

    private final String ROLE_API_URL = "/api" + ManagementApiLocations.ROLE;

    @MockBean
    private RoleService roleService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RoleUtils roleUtils;

    @BeforeEach
    public void setUp() {
        if (this.roleUtils == null) {
            this.roleUtils = new RoleUtils();
        }
    }

    @Test
    public void updateRole_updatesExistingRole_returnRole() throws Exception {

        //Assign
        RoleCreateDto dto = this.roleUtils.createDefaultRoleCreateDto();
        RoleModel model = this.roleUtils.createRoleModel();
        when(roleService.updateRole(any())).thenReturn(model);

        //Act
        String response = this.mockMvc.perform(put(ROLE_API_URL).secure(false)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        RoleReadDto createdDto = objectMapper.readValue(response, RoleReadDto.class);

        //Assert
        assertThat(createdDto).isNotNull();
        assertThat(createdDto.getId()).isEqualTo(1L);
        assertThat(createdDto.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void updateRole_passNotExistingRoleId_throwsItemNotFoundException() throws Exception {

        //Assign
        RoleUpdateDto dto = this.roleUtils.createDefaultRoleUpdateDto();
        when(roleService.updateRole(any())).thenThrow(ItemNotFoundException.class);

        //Act
        String response = this.mockMvc.perform(put(ROLE_API_URL).secure(false)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void updateRole_passExistingRoleName_throwsDataIntegrityViolationException() throws Exception {

        //Assign
        RoleUpdateDto dto = this.roleUtils.createDefaultRoleUpdateDto();
        when(roleService.updateRole(any())).thenThrow(DataIntegrityViolationException.class);

        //Act
        String response = this.mockMvc.perform(put(ROLE_API_URL).secure(false)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getStatus()).isEqualTo(HttpStatus.CONFLICT);
    }
}
