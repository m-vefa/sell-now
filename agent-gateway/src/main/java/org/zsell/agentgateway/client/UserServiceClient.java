package org.zsell.agentgateway.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zsell.agentgateway.model.auth.AuthenticationRequest;
import org.zsell.agentgateway.model.auth.UserProfile;

@FeignClient(value = "userServiceClient", url = "${user.service.url}")

public interface UserServiceClient {

    @PostMapping("/auth/login")
    UserProfile login(@RequestBody AuthenticationRequest  authenticationRequest);
}