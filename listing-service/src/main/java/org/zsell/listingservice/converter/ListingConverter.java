package org.zsell.listingservice.converter;

import org.springframework.stereotype.Component;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.model.ListingCreateRequest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ListingConverter implements Function<Listing, ListingCreateRequest> {

    @Override
    public ListingCreateRequest apply(Listing listing) {
        return ListingCreateRequest.builder()
                .title(listing.getTitle())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .build();
    }

}
