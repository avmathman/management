package com.invent.management.api.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.api.controllers.user.dto.UserReadDto;
import com.invent.management.api.controllers.user.dto.UserCreateDto;
import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.user.UserModel;
import com.invent.management.domain.user.UserService;
import com.invent.management.utils.RoleUtils;
import com.invent.management.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class CreateUserApiTest {

    private final String USER_API_URL = "/api" + ManagementApiLocations.USER;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserUtils userUtils;
    private RoleUtils roleUtils;
    private final String TEST_ROLE = "TEST";

    @BeforeEach
    public void setUp() {
        if (this.userUtils == null || this.roleUtils == null) {
            this.userUtils = new UserUtils();
            this.roleUtils = new RoleUtils();
        }
    }

    @Test
    public void createUser_createNewUser_returnNewUser() throws Exception {

        //Assign
        List<String> roles = Arrays.asList(TEST_ROLE);
        UserCreateDto dto = this.userUtils.createDefaultUserCreateDto(roles);
        UserModel model = this.userUtils.createDefaultUserModel(roles);

        when(userService.createUser(any())).thenReturn(model);

        //Act
        String response = this.mockMvc.perform(post(USER_API_URL)
            .secure(false)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

        UserReadDto createdDto = objectMapper.readValue(response, UserReadDto.class);

        //Assert
        assertThat(createdDto).isNotNull();
        assertThat(createdDto.getFirstname()).isEqualTo(dto.getFirstname());
        assertThat(createdDto.getLastname()).isEqualTo(dto.getLastname());
        assertThat(createdDto.isEnabled()).isEqualTo(dto.isEnabled());
        assertThat(createdDto.getRoles()).isEqualTo(dto.getRoles());
    }

    @Test
    public void createUser_duplicateRoleName_throwsItemNotFoundException() throws Exception {

        //Assign
        List<String> roles = Arrays.asList(TEST_ROLE);
        UserCreateDto dto = this.userUtils.createDefaultUserCreateDto(roles);
        UserModel model = this.userUtils.createDefaultUserModel(roles);

        when(userService.createUser(any())).thenThrow(ItemNotFoundException.class);

        //Act
        String response = this.mockMvc.perform(post(USER_API_URL)
            .secure(false)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorResponse error = objectMapper.readValue(response, ApiErrorResponse.class);

        //Assert
        assertThat(error.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
