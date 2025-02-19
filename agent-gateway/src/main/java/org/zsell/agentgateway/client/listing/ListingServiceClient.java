package org.zsell.agentgateway.client.listing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zsell.agentgateway.model.response.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.response.listing.ListingCreateResponse;

@FeignClient(value = "ListingServiceClient", url = "${listing.service.url}")
public interface ListingServiceClient {

    @PostMapping(value = ("/listing"))
    ListingCreateResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest);

}
