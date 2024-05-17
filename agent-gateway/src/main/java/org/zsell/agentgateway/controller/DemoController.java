package org.zsell.agentgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.agentgateway.model.auth.AuthenticationRequest;


@RestController
public class DemoController {


    @PostMapping("/xpath")
    public String login() {
        return "elkrwelkre";
    }


    @GetMapping("/error/accedd-denied")
    public String logisn(AuthenticationRequest authenticationRequest) {
        return "hataaaa";
    }


}
