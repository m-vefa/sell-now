package org.zsell.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.zsell.userservice.converter.AuthenticationResponseConverter;
import org.zsell.userservice.domain.User;
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

        Example<User> userExample = Example.of(User.builder()
                .email(authenticationRequest.getEmail())
                .password(authenticationRequest.getPassword())
                        .isActive(true)
                .build());
       User user =  userRepository.findOne(userExample).orElseThrow(AuthenticationFailedException::new);
        if(user!=null){
         return authenticationResponseConverter.convert(user);
        }
        return null;
    }
}
