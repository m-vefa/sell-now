package org.zsell.agentgateway.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private Boolean isActive;

}