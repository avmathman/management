package com.invent.management.api.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invent.management.annotation.ManagementIntegrationTest;
import com.invent.management.api.ManagementApiLocations;
import com.invent.management.api.advices.ApiErrorResponse;
import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ManagementIntegrationTest
public class DeleteUserApiTest {

    private final String USER_API_URL = "/api" + ManagementApiLocations.USER;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deleteRole_deleteUserById_void() throws Exception {

        //Assign
        String userId = "1";
        doNothing().when(userService).deleteUser(anyLong());

        //Act
        this.mockMvc.perform(delete(USER_API_URL + "/" + userId)
            .secure(false)
            .contentType("application/json"))
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse()
            .getContentAsString();
    }

    @Test
    public void deleteUser_deleteNotExistingUserByUserId_throwsItemNotFoundException() throws Exception {

        //Assign
        String userId = "1";
        doThrow(ItemNotFoundException.class).when(userService).deleteUser(anyLong());

        //Act
        String response = this.mockMvc.perform(delete(USER_API_URL + "/" + userId)
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
