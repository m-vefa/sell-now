package zsell.com.searchservice.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.exception.ListingSaveException;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private static final String LISTING_INDEX = "listings";

    public void save(Listing listing) {
        try {
            elasticsearchClient.index(i->i.index(LISTING_INDEX).id(listing.getListingId()).document(listing));
        }
        catch (IOException e) {
            throw new ListingSaveException("",listing.getListingId());
        }
    }
    public List<Listing> executeQuery(BoolQuery boolQuery) {
        Query query = new Query.Builder().bool(boolQuery).build();
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(LISTING_INDEX)
                .query(query)
                .build();

        return search(searchRequest);
    }
    private List<Listing> search(SearchRequest searchRequest) {
        try {
            SearchResponse<Listing> searchResponse = elasticsearchClient.search(searchRequest, Listing.class);
            return searchResponse.hits().hits().stream()
                    .map(Hit::source)
                    .toList();
        } catch (IOException | ElasticsearchException e) {
            log.error(String.valueOf(e));
            return List.of();
        }
    }
}