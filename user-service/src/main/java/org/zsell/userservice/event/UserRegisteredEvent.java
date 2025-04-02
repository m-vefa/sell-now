package org.zsell.userservice.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegisteredEvent extends ApplicationEvent {

    private UserDTO userDTO;

    public UserRegisteredEvent(Object source, UserDTO userDTO) {
        super(source);
        this.userDTO = userDTO;
    }

}
