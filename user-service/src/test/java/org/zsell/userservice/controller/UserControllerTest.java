package org.zsell.userservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.zsell.userservice.model.respose.user.UserResponse;
import org.zsell.userservice.service.UserService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        List<UserResponse> users = Arrays.asList(new UserResponse(/* construct user response */));
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value(users.get(0).getEmail()));
    }

    @Test
    void testGetUserById() throws Exception {
        UserResponse user = new UserResponse(/* construct user response */);
        Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getEmail()));
    }


    @Test
    void testUpdateUser() throws Exception {
        UserResponse userResponse = new UserResponse(/* construct user response */);
        Mockito.when(userService.updateUser(Mockito.anyInt(), Mockito.any())).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                        .contentType("application/json"))
                       // .content())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(userResponse.getEmail()));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService, Mockito.times(1)).deleteUser(Mockito.anyInt());
    }
}
