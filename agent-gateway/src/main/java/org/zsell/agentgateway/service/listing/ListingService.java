package org.zsell.agentgateway.service.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zsell.agentgateway.client.listing.ListingServiceClient;
import org.zsell.agentgateway.model.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.listing.ListingResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListingService {


    private final ListingServiceClient realtyServiceClient;

    public ListingResponse createListing(ListingCreateRequest listingCreateRequest) {
        return  realtyServiceClient.createListing(listingCreateRequest);
    }
}
