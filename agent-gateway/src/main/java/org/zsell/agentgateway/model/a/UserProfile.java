package org.zsell.agentgateway.model.a;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
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