package org.zsell.listingservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.zsell.listingservice.model.ListingResponse;
import org.zsell.listingservice.service.ListingService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ListingController.class)
class ListingControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private ListingService listingService;


//    Pattern: ExpectedBehavior_whenMethodUnderTest_withScenario

    @Test
    void givenValidRequest_whenGetListings_thenReturnListOfListings() throws Exception {

        ListingResponse listingResponse1 = ListingResponse.builder()
                .title("Test Listing 1")
                .description("Test Description 1")
                .price(100)
                .build();

        ListingResponse listingResponse2 = ListingResponse.builder()
                .title("Test Listing 2")
                .description("Test Description 2")
                .price(200)
                .build();

        List<ListingResponse> listings = Arrays.asList(listingResponse1, listingResponse2);


        when(listingService.getAllListing()).thenReturn(listings);


        mockMvc.perform(get("/listings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Listing 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[0].price").value(100.0))
                .andExpect(jsonPath("$[1].title").value("Test Listing 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"))
                .andExpect(jsonPath("$[1].price").value(200.0));
    }


}