package org.zsell.agentgateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.agentgateway.model.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.listing.ListingResponse;
import org.zsell.agentgateway.service.listing.ListingService;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;

    @PostMapping
    public ListingResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest) {
        return listingService.createListing(listingCreateRequest);
    }
}


