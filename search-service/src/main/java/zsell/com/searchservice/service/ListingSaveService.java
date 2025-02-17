package zsell.com.searchservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zsell.com.searchservice.client.ListingServiceClient;
import zsell.com.searchservice.converter.ListingConverter;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.exception.ListingSaveException;
import zsell.com.searchservice.repository.ElasticSearchService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ListingSaveService {
    private final ListingServiceClient listingServiceClient;
    private final ListingConverter listingConverter;
    private final ElasticSearchService elasticSearchService;

    public void saveListing(Integer listingId) {
        try {
            Map<String, Object> getListing = listingServiceClient.getListing(listingId);
            Listing listing = listingConverter.apply(getListing);
            elasticSearchService.save(listing);
        } catch (Exception e) {
            throw new ListingSaveException(e.toString());
        }

    }
}
