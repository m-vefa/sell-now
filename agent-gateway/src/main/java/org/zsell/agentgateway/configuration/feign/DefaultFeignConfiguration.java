package org.zsell.agentgateway.configuration.feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DefaultFeignConfiguration {

    @Bean
    @Primary
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default();
    }
}