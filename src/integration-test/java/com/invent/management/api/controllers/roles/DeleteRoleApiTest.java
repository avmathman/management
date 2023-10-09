package com.invent.management.api.controllers.roles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.domain.exception.ItemNotFoundException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class DeleteRoleApiTest {

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
        doNothing().when(roleService).deleteRole(anyLong());

        //Act
        String response = this.mockMvc.perform(delete(ROLE_API_URL + "/" + roleId)
            .secure(false)
            .contentType("application/json"))
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse()
            .getContentAsString();
    }

    @Test
    public void deleteRole_passNotExistingRoleId_throwsItemNotFoundException() throws Exception {

        //Assign
        String roleId = "1";
        doThrow(ItemNotFoundException.class).when(roleService).deleteRole(anyLong());

        //Act
        String response = this.mockMvc.perform(delete(ROLE_API_URL + "/" + roleId)
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
