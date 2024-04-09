package org.zsell.agentgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class AgentGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(AgentGatewayApplication.class, args);
    }

}
