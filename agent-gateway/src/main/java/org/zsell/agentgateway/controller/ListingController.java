package org.zsell.agentgateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.agentgateway.model.response.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.response.listing.ListingCreateResponse;
import org.zsell.agentgateway.model.response.listing.ListingResponse;
import org.zsell.agentgateway.service.listing.ListingService;

import java.util.List;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;

    @PostMapping
    public ListingCreateResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest) {
        return listingService.createListing(listingCreateRequest);
    }
    @PostMapping("/publish/{listingId}")
    public void publishListing(@PathVariable Integer listingId) {
        listingService.publishListing(listingId);
    }

    @GetMapping("/firm/{firmId}")
    public List<ListingResponse> getListingsByFirmId(@PathVariable Integer firmId) {
        return listingService.getListingsByFirmId(firmId);
    }

}


