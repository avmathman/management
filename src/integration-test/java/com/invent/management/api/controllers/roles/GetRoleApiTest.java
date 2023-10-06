package com.invent.management.api.controllers.roles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.api.controllers.roles.dto.RoleReadDto;
import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.role.RoleModel;
import com.invent.management.domain.role.RoleService;
import com.invent.management.utils.RoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class GetRoleApiTest {

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
    public void deleteRole_passRoleId_void() throws Exception {

        //Assign
        String roleId = "1";
        RoleModel model = this.roleUtils.createRoleModel();
        when(roleService.getRole(anyLong())).thenReturn(model);

        //Act
        String response = this.mockMvc.perform(get(ROLE_API_URL + "/" + roleId)
            .secure(false)
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        RoleReadDto updatedDto = objectMapper.readValue(response, RoleReadDto.class);

        //Assert
        assertThat(updatedDto).isNotNull();
        assertThat(updatedDto.getId()).isEqualTo(model.getId());
        assertThat(updatedDto.getName()).isEqualTo(model.getName());
    }

    @Test
    public void deleteRole_duplicateRoleName_throwsDuplicateItemException() throws Exception {

        //Assign
        String roleId = "1";
        doThrow(ItemNotFoundException.class).when(roleService).getRole(anyLong());

        //Act
        String response = this.mockMvc.perform(get(ROLE_API_URL + "/" + roleId)
            .secure(false)
            .contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
