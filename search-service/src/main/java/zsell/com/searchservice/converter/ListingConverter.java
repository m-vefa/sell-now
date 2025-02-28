package zsell.com.searchservice.converter;

import org.springframework.stereotype.Component;
import zsell.com.searchservice.domain.Listing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Function;

@Component
public class ListingConverter implements Function<Map<String ,Object>, Listing> {
    @Override
    public Listing apply(Map<String ,Object>listing) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        LocalDateTime createdDate = LocalDateTime.parse((String) listing.get("createdDate"), formatter);
        LocalDateTime updatedDate = LocalDateTime.parse((String) listing.get("createdDate"), formatter);

        return Listing.builder()
                .listingId((Integer) listing.get("listingId"))
                .firmId((Integer) listing.get("firmId"))
                .price((Integer) listing.get("price"))
                .title((String) listing.get("title"))
                .description((String) listing.get("description"))
                .statusId((Integer) listing.get("statusId"))
                .categoryId((Integer) listing.get("categoryId"))
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}