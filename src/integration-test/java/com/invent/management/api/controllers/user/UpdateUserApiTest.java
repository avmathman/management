package com.invent.management.api.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.api.controllers.user.dto.UserCreateDto;
import com.invent.management.api.controllers.user.dto.UserReadDto;
import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.user.UserModel;
import com.invent.management.domain.user.UserService;
import com.invent.management.utils.RoleUtils;
import com.invent.management.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class UpdateUserApiTest {

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
    public void updateUser_updatesExistingUser_returnUser() throws Exception {

        //Assign
        List<String> roles = Arrays.asList(TEST_ROLE);
        UserCreateDto dto = this.userUtils.createDefaultUserCreateDto(roles);
        UserModel model = this.userUtils.createDefaultUserModel(roles);
        when(userService.updateUser(any())).thenReturn(model);

        //Act
        String response = this.mockMvc.perform(put(USER_API_URL)
                        .secure(false)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserReadDto updatedDto = objectMapper.readValue(response, UserReadDto.class);

        //Assert
        assertThat(updatedDto).isNotNull();
        assertThat(updatedDto.getId()).isEqualTo(1L);
        assertThat(updatedDto.getFirstname()).isEqualTo(dto.getFirstname());
    }

    @Test
    public void updateUser_passNotExistingUser_throwsItemNotFoundException() throws Exception {

        //Assign
        List<String> roles = Arrays.asList(TEST_ROLE);
        UserCreateDto dto = this.userUtils.createDefaultUserCreateDto(roles);
        when(userService.updateUser(any())).thenThrow(ItemNotFoundException.class);

        //Act
        String response = this.mockMvc.perform(put(USER_API_URL)
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

    @Test
    public void updateUser_passNotExistingRoleName_throwsItemNotFoundException() throws Exception {

        //Assign
        List<String> roles = Arrays.asList("NOT_EXIST");
        UserCreateDto dto = this.userUtils.createDefaultUserCreateDto(roles);
        when(userService.updateUser(any())).thenThrow(ItemNotFoundException.class);

        //Act
        String response = this.mockMvc.perform(put(USER_API_URL)
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

    @Test
    public void updateUser_passExistingEmail_throwsDataIntegrityViolationException() throws Exception {

        //Assign
        List<String> roles = Arrays.asList(TEST_ROLE);
        UserCreateDto dto = this.userUtils.createDefaultUserCreateDto(roles);
        when(userService.updateUser(any())).thenThrow(DataIntegrityViolationException.class);

        //Act
        String response = this.mockMvc.perform(put(USER_API_URL)
                        .secure(false)
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
