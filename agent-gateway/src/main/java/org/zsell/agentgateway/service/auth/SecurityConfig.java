package org.zsell.agentgateway.service.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.zsell.agentgateway.controller.advice.JwtAuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
@Component
public class SecurityConfig {
    private final AuthenticationTokenFilter authenticationTokenFilter;
    private final AuthenticationService authenticationService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(AuthenticationTokenFilter authenticationTokenFilter, AuthenticationService authenticationService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.authenticationTokenFilter = authenticationTokenFilter;
        this.authenticationService = authenticationService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .httpBasic(Customizer.withDefaults())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.requestMatchers("/auth/**","/swagger-ui.html/*")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterAfter(new AuthenticationTokenFilter(authenticationService,
                        jwtAuthenticationEntryPoint), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}


