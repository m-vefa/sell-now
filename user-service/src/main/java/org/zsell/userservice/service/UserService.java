package org.zsell.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsell.userservice.converter.UserResponseConverter;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.enums.UserStatus;
import org.zsell.userservice.exception.UserNotFoundException;
import org.zsell.userservice.model.request.user.UserCreateRequest;
import org.zsell.userservice.model.respose.user.UserResponse;
import org.zsell.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userResponseConverter::convert).toList();
    }

    public UserResponse getUserById(Integer id) {
        return userRepository.findById(id).map(userResponseConverter::convert).orElseThrow(UserNotFoundException::new);

    }

    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .username(userCreateRequest.getUsername())
                .password(userCreateRequest.getPassword())
                .email(userCreateRequest.getEmail())
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .address(userCreateRequest.getAddress())
                .statusId(UserStatus.PENDING_APPROVAL.getId())
                .build();
        userRepository.save(user);

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
            userRepository.save(user);
            return userResponseConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    public void deleteUser(Integer id) {

        userRepository.deleteById(id);
    }
}
