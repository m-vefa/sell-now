package zsell.com.searchservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.repository.ElasticSearchService;

@Service
@RequiredArgsConstructor
public class ListingSaveService {

    private final ElasticSearchService elasticSearchService;
    public void saveListing(Listing listing) {
        elasticSearchService.save(listing);
    }

}
