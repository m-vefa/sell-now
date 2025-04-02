package zsell.com.listingservice.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zsell.com.listingservice.domain.Listing;
import zsell.com.listingservice.model.ListingResponse;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ListingResponseConverterTest {
    @InjectMocks
    private ListingResponseConverter listingResponseConverter;


    @Test
    void returnListingResponse_whenApply_withValidListing() {
        Listing listing = Listing.builder().listingId(31).firmId(3).build();

        ListingResponse actualListingResponse = listingResponseConverter.apply(listing);

        assertThat(actualListingResponse.getListingId()).isEqualTo(31);
        assertThat(actualListingResponse.getFirmId()).isEqualTo(3);

    }

    @Test
    void returnListListingResponse_whenApply_withValidListing() {
        Listing listing = Listing.builder().listingId(113).firmId(32).build();
        Listing listing1 = Listing.builder().listingId(112).firmId(33).build();
        List<Listing> listingList = Arrays.asList(listing1, listing);

        List<ListingResponse> actualListingResponseList = listingResponseConverter.applyToList(listingList);

        assertThat(actualListingResponseList).extracting(ListingResponse::getListingId).containsExactlyInAnyOrder(113, 112);
        assertThat(actualListingResponseList).extracting(ListingResponse::getFirmId).containsExactlyInAnyOrder(32, 33);

    }
}