package org.zsell.userservice.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventListener {

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        UserDTO userDTO = event.getUserDTO();
        
        System.out.println("Yeni Kullanıcı Kaydı: " + userDTO.getUsername());
        System.out.println("E-posta: " + userDTO.getEmail());
        System.out.println("Adı: " + userDTO.getFullName());
    }
}
