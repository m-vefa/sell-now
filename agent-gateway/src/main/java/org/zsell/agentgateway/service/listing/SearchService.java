package org.zsell.agentgateway.service.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zsell.agentgateway.client.listing.SearchServiceClient;
import org.zsell.agentgateway.model.request.ListingFilterRequest;
import org.zsell.agentgateway.model.response.auth.UserProfile;
import org.zsell.agentgateway.model.response.listing.ListingResponse;
import org.zsell.agentgateway.util.UserAuthUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {


    private final SearchServiceClient searchServiceClient;
    public List<ListingResponse> getFilteredListings(ListingFilterRequest listingFilterRequest) {
        final UserProfile userAuthUtils = UserAuthUtils.getUser();
        listingFilterRequest.setFirmId(userAuthUtils.getFirmId());
        return searchServiceClient.getFilteredListings(listingFilterRequest);

    }
}
