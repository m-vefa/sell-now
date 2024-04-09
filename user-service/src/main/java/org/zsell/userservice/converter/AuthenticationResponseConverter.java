package org.zsell.userservice.converter;

import org.springframework.stereotype.Component;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.model.respose.AuthenticationResponse;
@Component
public class AuthenticationResponseConverter {

        public AuthenticationResponse convert(User user) {
            return AuthenticationResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .phone(user.getPhoneNumber())
                    .isActive(user.isActive())
                    .build();
        }
    }
