package org.zsell.agentgateway.model.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserProfile implements Serializable {

    private static final long serialVersionUID = 5957551812170018960L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private Boolean isActive;

}