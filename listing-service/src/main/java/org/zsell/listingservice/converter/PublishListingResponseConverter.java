package org.zsell.listingservice.converter;

import org.springframework.stereotype.Component;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.model.ListingPublishResponse;

import java.util.function.Function;

@Component
public class PublishListingResponseConverter implements Function<Listing, ListingPublishResponse> {

    @Override
    public ListingPublishResponse apply(Listing listing) {
        return ListingPublishResponse.builder()
                .id(listing.getListingId())
                .activateId(listing.getActivateId())
                .build();
    }

}
