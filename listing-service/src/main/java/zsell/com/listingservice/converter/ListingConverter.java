package zsell.com.listingservice.converter;

import org.springframework.stereotype.Component;
import zsell.com.listingservice.domain.Listing;
import zsell.com.listingservice.enums.ListingStatus;
import zsell.com.listingservice.model.create.ListingCreateRequest;
@Component
public class ListingConverter {
    public Listing convert(ListingCreateRequest listingCreateRequest) {
        return Listing.builder()
                .statusId(ListingStatus.DRAFT.getId())
                .title(listingCreateRequest.getTitle())
                .description(listingCreateRequest.getDescription())
                .price(listingCreateRequest.getPrice())
                .categoryId(listingCreateRequest.getCategoryId())
                .firmId(listingCreateRequest.getFirmId())
                .build();
    }
}
