package org.zsell.agentgateway.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zsell.agentgateway.model.request.user.UserCreateRequest;
import org.zsell.agentgateway.model.response.auth.AuthenticationRequest;
import org.zsell.agentgateway.model.response.auth.UserProfile;
import org.zsell.agentgateway.model.response.user.UserResponse;

@FeignClient(value = "userServiceClient", url = "${user.service.url}")

public interface UserServiceClient {

    @PostMapping("/auth/login")
    UserProfile login(@RequestBody AuthenticationRequest  authenticationRequest);

    @PostMapping("/api/users")
    UserResponse createUser(UserCreateRequest userCreateRequest);
}