package org.zsell.agentgateway.model.auth;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter {

    public UserProfile apply(Claims claims) {
        UserProfile userProfile = new UserProfile();

        userProfile.setId(getIntegerValue(claims, "id"));
        userProfile.setFirstName(getValue(claims, "firstName"));
        userProfile.setLastName(getValue(claims, "lastName"));
        userProfile.setUserName(getValue(claims, "userName"));
        userProfile.setEmail(getValue(claims, "email"));
        userProfile.setPhone(getValue(claims, "phone"));
        userProfile.setIsActive(getBooleanValue(claims));
        return userProfile;
    }


    private String getValue(Claims claims, String key) {
        Object foundValue = claims.getOrDefault(key, StringUtils.EMPTY);
        return foundValue == null ? null : foundValue.toString();
    }

    private Boolean getBooleanValue(Claims claims) {
        Object foundValue = claims.getOrDefault("isActive", StringUtils.EMPTY);
        return foundValue == null ? null : Boolean.parseBoolean(foundValue.toString());
    }

    private Integer getIntegerValue(Claims claims, String key) {
        String value = getValue(claims, key);
        return StringUtils.isNotBlank(value) ? Integer.parseInt(value) : 0;
    }
}