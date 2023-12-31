package com.invent.management.api.controllers.roles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.api.controllers.roles.dto.RoleCreateDto;
import com.invent.management.api.controllers.roles.dto.RoleReadDto;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class CreateRoleApiTest {

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
    public void createRole_createNewRole_returnNewRole() throws Exception {

        //Assign
        RoleCreateDto dto = this.roleUtils.createDefaultRoleCreateDto();
        RoleModel model = this.roleUtils.createRoleModel();
        when(roleService.createRole(any())).thenReturn(model);

        //Act
        String response = this.mockMvc.perform(post(ROLE_API_URL).secure(false)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
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
    public void createRole_duplicateRoleName_throwsDataIntegrityViolationException() throws Exception {

        //Assign
        RoleCreateDto dto = this.roleUtils.createDefaultRoleCreateDto();
        when(roleService.createRole(any())).thenThrow(DataIntegrityViolationException.class);

        //Act
        String response = this.mockMvc.perform(post(ROLE_API_URL).secure(false)
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
