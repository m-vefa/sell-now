package org.zsell.agentgateway.service.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.zsell.agentgateway.model.a.UserProfile;

import java.util.Collection;
import java.util.Collections;

public class UserAuthentication  implements Authentication {
    private final UserProfile userProfile;
    private boolean authenticated = true;

    public UserAuthentication(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserProfile getDetails() {
        return userProfile;
    }

    @Override
    public Object getPrincipal() {
        return userProfile.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return userProfile.getFirstName() + " " + userProfile.getLastName();
    }


}
