package org.zsell.agentgateway.controller;

import jakarta.servlet.Filter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zsell.agentgateway.controller.advice.JwtAuthenticationEntryPoint;
import org.zsell.agentgateway.model.response.auth.UserProfile;
import org.zsell.agentgateway.service.auth.AuthenticationService;
import org.zsell.agentgateway.service.auth.UserAuthentication;
import org.zsell.agentgateway.service.listing.ListingService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ListingController.class)
class ListingControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private ListingService listingService;


    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @Test
    void publishListing_whenValidListingId_shouldReturnOk() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(1);
        UserAuthentication authentication = new UserAuthentication(userProfile);
        when(authenticationService.getUserAuthentication(any())).thenReturn(authentication);


        doNothing().when(listingService).publishListing(eq(1));
        ResultActions resultActions = mockMvc.perform(post("/listings/1/publish").contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        verify(listingService).publishListing((eq(1)));

    }
}
