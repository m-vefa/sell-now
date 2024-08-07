package org.zsell.agentgateway.client.listing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zsell.agentgateway.model.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.listing.ListingResponse;

@FeignClient(
        value = "ListingServiceClient",
        url = "${listing.service.url}"
)
public interface ListingServiceClient {

    @PostMapping(value = ("/listings"))
    ListingResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest);

}
