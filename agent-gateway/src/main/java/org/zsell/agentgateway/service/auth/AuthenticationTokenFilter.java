package org.zsell.agentgateway.service.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.zsell.agentgateway.controller.advice.JwtAuthenticationEntryPoint;
import org.zsell.agentgateway.exception.AuthenticationFailedException;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationTokenFilter extends GenericFilterBean {

    public static final String AUTHENTICATION_FAILED = "Authentication Failed";
    private final AuthenticationService authenticationService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            Authentication userAuthentication = authenticationService.getUserAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthenticationFailedException | ServletException e) {
            logger.error(AUTHENTICATION_FAILED, e);
            SecurityContextHolder.clearContext();

            jwtAuthenticationEntryPoint.commence(
                    (HttpServletRequest) servletRequest,
                    (HttpServletResponse) servletResponse,
                    new AuthenticationFailedException("hhhhhhhh")
            );
        }
    }
}
