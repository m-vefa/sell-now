package org.zsell.userservice.model.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String username;

    private String password;
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;
}