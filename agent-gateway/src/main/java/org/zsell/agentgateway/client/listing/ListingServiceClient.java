package org.zsell.agentgateway.client.listing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zsell.agentgateway.model.response.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.response.listing.ListingCreateResponse;
import org.zsell.agentgateway.model.response.listing.ListingResponse;

import java.util.List;

@FeignClient(value = "ListingServiceClient", url = "${listing.service.url}")
public interface ListingServiceClient {

    @PostMapping(value = ("/listings"))
    ListingCreateResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest);
    @PostMapping(value = ("/listings/{listingId}/publish"))
    void publishListing( @PathVariable Integer listingId);
    @GetMapping("/listings")
    List<ListingResponse> getListingsByFirmId(@RequestParam Integer firmId);
}
