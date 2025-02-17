package zsell.com.searchservice.converter;

import org.springframework.stereotype.Component;
import zsell.com.searchservice.domain.Listing;

import java.util.Map;
import java.util.function.Function;

@Component
public class ListingConverter implements Function<Map<String ,Object>, Listing> {
    @Override
    public Listing apply(Map<String ,Object>listing) {
        return Listing.builder()
                .listingId((Integer) listing.get("listingId"))
                .firmId((Integer) listing.get("firmId"))
                .price((Integer) listing.get("price"))
                .statusId((Integer) listing.get("statusId"))
                .categoryId((Integer) listing.get("categoryId"))
                .build();
    }
}