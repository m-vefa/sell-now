package org.zsell.agentgateway.service.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zsell.agentgateway.client.listing.ListingServiceClient;
import org.zsell.agentgateway.model.response.listing.ListingCreateRequest;
import org.zsell.agentgateway.model.response.listing.ListingCreateResponse;
import org.zsell.agentgateway.model.response.listing.ListingResponse;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingServiceClient listingServiceClient;
    public ListingCreateResponse createListing(ListingCreateRequest listingCreateRequest) {
        return listingServiceClient.createListing(listingCreateRequest);
    }
    public void publishListing(Integer listingId) {
        listingServiceClient.publishListing(listingId);
    }

    public List<ListingResponse> getListingsByFirmId(Integer firmId) {
       return  listingServiceClient.getListingsByFirmId(firmId);

    }
}
