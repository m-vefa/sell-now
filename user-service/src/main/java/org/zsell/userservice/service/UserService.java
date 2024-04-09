package org.zsell.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsell.userservice.converter.UserResponseConverter;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.model.request.user.UserCreateRequest;
import org.zsell.userservice.model.respose.user.UserResponse;
import org.zsell.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(userResponseConverter::convert).collect(Collectors.toList());
    }

    public UserResponse getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);


        return userOptional.map(userResponseConverter::convert).orElse(null);

    }

    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .email(userCreateRequest.getUsername())
                .email(userCreateRequest.getPassword())
                .email(userCreateRequest.getEmail())
                .email(userCreateRequest.getFirstName())
                .email(userCreateRequest.getLastName())
                .email(userCreateRequest.getPhoneNumber())
                .email(userCreateRequest.getAddress())
                .build();
        userRepository.save(user); // Save user to database using repository


        return userResponseConverter.convert(user);
    }

    public UserResponse updateUser(Integer id, UserCreateRequest userCreateRequest) {

        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = User.builder()
                    .email(userCreateRequest.getUsername())
                    .email(userCreateRequest.getPassword())
                    .email(userCreateRequest.getEmail())
                    .email(userCreateRequest.getFirstName())
                    .email(userCreateRequest.getLastName())
                    .email(userCreateRequest.getPhoneNumber())
                    .email(userCreateRequest.getAddress())
                    .build();
            userRepository.save(user); // Save user to database using repository
            return userResponseConverter.convert(user);
        }
        return null; // or throw an exception indicating user not found
    }

    public void deleteUser(Integer id) {

        userRepository.deleteById(id);
    }
}
