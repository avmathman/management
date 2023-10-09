package com.invent.management.api.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class GetUserApiTest {

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
    public void getUser_getUserByUserId_returnUser() throws Exception {

        //Assign
        String userId = "1";
        List<String> roles = Arrays.asList(TEST_ROLE);
        UserModel model = this.userUtils.createDefaultUserModel(roles);
        when(userService.getUser(anyLong())).thenReturn(model);

        //Act
        String response = this.mockMvc.perform(get(USER_API_URL + "/" + userId)
            .secure(false)
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        UserReadDto existingDto = objectMapper.readValue(response, UserReadDto.class);

        //Assert
        assertThat(existingDto).isNotNull();
        assertThat(existingDto.getId()).isEqualTo(model.getId());
        assertThat(existingDto.getFirstname()).isEqualTo(model.getFirstname());
    }

    @Test
    public void getUser_passNotExistingUserId_throwsItemNotFoundException() throws Exception {

        //Assign
        String userId = "1";
        doThrow(ItemNotFoundException.class).when(userService).getUser(anyLong());

        //Act
        String response = this.mockMvc.perform(get(USER_API_URL + "/" + userId)
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
