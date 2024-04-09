package org.zsell.userservice.converter;

import org.springframework.stereotype.Component;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.model.respose.user.UserResponse;

@Component
public class UserResponseConverter {

    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}