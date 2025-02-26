package org.zsell.userservice.model.respose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer statusId;
}
