package org.zsell.agentgateway.service.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zsell.agentgateway.client.UserServiceClient;
import org.zsell.agentgateway.model.request.user.UserCreateRequest;
import org.zsell.agentgateway.model.response.user.UserResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserServiceClient userServiceClient;

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userServiceClient.createUser(userCreateRequest);
    }


}