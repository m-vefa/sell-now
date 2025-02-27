package org.zsell.agentgateway.client.listing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.zsell.agentgateway.model.request.ListingFilterRequest;
import org.zsell.agentgateway.model.response.listing.ListingResponse;

import java.util.List;

@FeignClient(value = "SearchServiceClient", url = "${search.service.url}" )
public interface SearchServiceClient {
    @GetMapping("/listings/search")
    List<ListingResponse> getFilteredListings(@RequestBody ListingFilterRequest listingFilterRequest);
}
