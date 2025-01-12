package zsell.com.searchservice.converter;

import org.springframework.stereotype.Component;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.model.ListingCreateRequest;

import java.util.function.Function;

@Component
public class ListingConverter implements Function<Listing, ListingCreateRequest> {

    @Override
    public ListingCreateRequest apply(Listing listing) {
        return ListingCreateRequest.builder()
                .listingId(listing.getListingId())
                .build();
    }

}