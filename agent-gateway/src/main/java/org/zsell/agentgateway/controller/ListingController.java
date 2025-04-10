package org.zsell.agentgateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.agentgateway.model.request.ListingFilterRequest;
import org.zsell.agentgateway.model.response.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.response.listing.ListingCreateResponse;
import org.zsell.agentgateway.model.response.listing.ListingResponse;
import org.zsell.agentgateway.service.listing.SearchService;
import org.zsell.agentgateway.service.listing.ListingService;

import java.util.List;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;
    private final SearchService searchService;

    @PostMapping
    public ListingCreateResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest) {
        return listingService.createListing(listingCreateRequest);
    }
    @PostMapping("/{listingId}/publish")
    public void publishListing(@PathVariable Integer listingId) {
        listingService.publishListing(listingId);
    }

    @GetMapping()
    public List<ListingResponse> getListingsByFirmId() {
        return listingService.getListingsByFirmId();
    }

    @GetMapping("/search")
    public List<ListingResponse> getFilteredListings(@RequestBody ListingFilterRequest filterRequest) {
        return searchService.getFilteredListings(filterRequest);
    }

}


