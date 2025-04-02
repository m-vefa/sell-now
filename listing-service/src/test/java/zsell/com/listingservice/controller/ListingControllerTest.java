package zsell.com.listingservice.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import zsell.com.listingservice.model.ListingResponse;
import zsell.com.listingservice.model.create.ListingCreateResponse;
import zsell.com.listingservice.service.ListingService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ListingController.class)
class ListingControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private ListingService listingService;
    @Test
    void returnListings_whenGetListingsByFirmId_withValidRequest() throws Exception {
        Integer firmId = 3;
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

        when(listingService.getListingsByFirmId(firmId)).thenReturn(listings);

        mockMvc.perform(get("/listings/firm/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void returnListing_whenCreateListing_withValidRequest() throws Exception {
        ListingCreateResponse listingCreateResponse = ListingCreateResponse.builder().listingId(12).build();
        when(listingService.createListing(any())).thenReturn(listingCreateResponse);
        mockMvc.perform(post("/listings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
