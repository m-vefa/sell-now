package zsell.com.listingservice.converter;

import org.springframework.stereotype.Component;
import zsell.com.listingservice.domain.Listing;
import zsell.com.listingservice.model.ListingResponse;

import java.util.List;
import java.util.function.Function;

@Component
public class ListingResponseConverter implements  Function<Listing,ListingResponse>{

    @Override
    public ListingResponse apply(Listing listing) {
        return ListingResponse.builder()
                .listingId(listing.getListingId())
                .firmId(listing.getFirmId())
                .title(listing.getTitle())
                .title(listing.getDescription())
                .price(listing.getPrice())
                .build();
    }
    public List<ListingResponse> applyToList(List<Listing> listings){
        return listings.stream().map(this).toList();

    }
}
