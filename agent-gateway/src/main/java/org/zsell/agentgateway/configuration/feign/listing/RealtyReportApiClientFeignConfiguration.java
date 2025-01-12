package org.zsell.agentgateway.configuration.feign.listing;

import feign.Contract;
import feign.Request;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class RealtyReportApiClientFeignConfiguration {

    private static final int CONNECT_TIMEOUT_MILLIS = 5000;
    private static final int READ_TIMEOUT_MILLIS = 5000;
    private static final boolean FOLLOW_REDIRECTS = true;

    @Bean
    public Contract useRealtyFavoriteApiClientFeignAnnotations() {
        return new SpringMvcContract();
    }

    @Bean
    public ListingClientErrorDecoder realtyFavoriteApiClientErrorDecoder() {
        return new ListingClientErrorDecoder();
    }
    @Bean
    public Request.Options options() {
        return new Request.Options(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS, READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS, FOLLOW_REDIRECTS);
    }
}