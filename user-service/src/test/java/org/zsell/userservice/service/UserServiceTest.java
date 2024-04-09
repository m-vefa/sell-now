package org.zsell.userservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zsell.userservice.converter.UserResponseConverter;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.model.respose.user.UserResponse;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserResponseConverter userResponseConverter;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserResponse() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        UserResponse expectedResponse = UserResponse.builder()
                .firstName("testuser")
                .email("test@example.com")
                .build();

        when(userResponseConverter.convert(user)).thenReturn(expectedResponse);

        // When
      //  UserResponse userResponse = userService.getAllUsers();

        // Then
   //     assertEquals(expectedResponse, userResponse);
    }
}
