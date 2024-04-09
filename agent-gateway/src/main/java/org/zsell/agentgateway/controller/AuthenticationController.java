package org.zsell.agentgateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.agentgateway.model.AuthenticationRequest;
import org.zsell.agentgateway.model.AuthenticationResponse;
import org.zsell.agentgateway.service.auth.TokenManager;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final TokenManager tokenManager;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return tokenManager.login(authenticationRequest);
    }

    @PostMapping("/register")
    public String register() {
        return "register";
    }

}
