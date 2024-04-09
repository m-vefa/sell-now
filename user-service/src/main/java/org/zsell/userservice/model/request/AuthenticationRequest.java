package org.zsell.userservice.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
    @NotBlank(message = "signInRequest.email.notBlank")
    @Email(message = "signInRequest.email.notValid")
    private String email;
    @NotBlank(message = "signInRequest.password.notBlank")
    private String password;
}
