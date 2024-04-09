package org.zsell.userservice.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zsell.userservice.domain.User;
import org.zsell.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndGetUser() {

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setEmail("test@example.com");

        User savedUser = userRepository.save(user);


        Optional<User> retrievedUserOptional = userRepository.findById(savedUser.getId());
        assertTrue(retrievedUserOptional.isPresent());
        User retrievedUser = retrievedUserOptional.get();
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("testpassword", retrievedUser.getPassword());
        assertEquals("test@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testFindByUsername() {

        User user = new User();
        user.setUsername("testuser2");
        userRepository.save(user);


        Optional<User> retrievedUserOptional = userRepository.findById(1);
        assertTrue(retrievedUserOptional.isPresent());
        User retrievedUser = retrievedUserOptional.get();
        assertEquals("testuser2", retrievedUser.getUsername());
    }

    @Test
    public void testDeleteUser() {

        User user = new User();
        user.setUsername("testuser3");
        User savedUser = userRepository.save(user);


        userRepository.delete(savedUser);


        Optional<User> retrievedUserOptional = userRepository.findById(savedUser.getId());
        assertTrue(retrievedUserOptional.isEmpty());
    }
}
