package org.zsell.userservice.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserXService {

    private final ApplicationEventPublisher eventPublisher;

    public UserXService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String username, String email, String fullName) {
        UserDTO userDTO = new UserDTO(username, email, fullName);
        
        UserRegisteredEvent event = new UserRegisteredEvent(UserXService.class, userDTO);
        eventPublisher.publishEvent(event);
        
    }
}
