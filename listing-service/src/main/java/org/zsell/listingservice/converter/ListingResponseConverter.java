package org.zsell.listingservice.converter;

import org.springframework.stereotype.Component;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.model.ListingResponse;

@Component
public class ListingResponseConverter {

    public ListingResponse convert(Listing listing) {
        return ListingResponse.builder()
                .listingId(listing.getListingId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .createdDate(listing.getCreatedDate())
                .build();
    }
}