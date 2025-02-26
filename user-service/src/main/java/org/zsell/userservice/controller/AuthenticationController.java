package org.zsell.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.userservice.model.request.AuthenticationRequest;
import org.zsell.userservice.model.request.user.UserCreateRequest;
import org.zsell.userservice.model.respose.AuthenticationResponse;
import org.zsell.userservice.model.respose.user.UserResponse;
import org.zsell.userservice.service.AuthenticationService;
import org.zsell.userservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }
    @PostMapping("/register")
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

}

