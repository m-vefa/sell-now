package org.zsell.userservice.converter;

import org.springframework.stereotype.Component;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.model.request.user.UserCreateRequest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Function<User, UserCreateRequest> {

    @Override
    public UserCreateRequest apply(User user) {
        return UserCreateRequest.builder()
                .email(user.getEmail())
                .build();
    }

    public List<UserCreateRequest> applyToList(List<User> users) {
        return users.stream().map(this).collect(Collectors.toList());
    }
}
