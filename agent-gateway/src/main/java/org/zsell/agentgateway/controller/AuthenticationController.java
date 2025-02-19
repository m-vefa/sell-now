package org.zsell.agentgateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.agentgateway.model.request.user.UserCreateRequest;
import org.zsell.agentgateway.model.response.auth.AuthenticationRequest;
import org.zsell.agentgateway.model.response.auth.AuthenticationResponse;
import org.zsell.agentgateway.model.response.user.UserResponse;
import org.zsell.agentgateway.service.auth.TokenManager;
import org.zsell.agentgateway.service.create.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final TokenManager tokenManager;
    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return tokenManager.login(authenticationRequest);
    }

    @PostMapping("/register")
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

}
