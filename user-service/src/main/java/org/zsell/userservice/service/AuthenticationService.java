package org.zsell.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zsell.userservice.converter.AuthenticationResponseConverter;
import org.zsell.userservice.enums.UserStatus;
import org.zsell.userservice.exception.AuthenticationFailedException;
import org.zsell.userservice.model.request.AuthenticationRequest;
import org.zsell.userservice.model.respose.AuthenticationResponse;
import org.zsell.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationResponseConverter authenticationResponseConverter;


    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        return userRepository.findByEmailAndPasswordAndStatusId(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword(), UserStatus.ACTIVE.getId())
                .map(authenticationResponseConverter::convert)
                .orElseThrow(AuthenticationFailedException::new);
    }
}
