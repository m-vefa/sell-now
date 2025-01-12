package zsell.com.searchservice.service;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.domain.ListingFilterRequest;
import zsell.com.searchservice.repository.ElasticSearchService;
import zsell.com.searchservice.repository.QueryBuildersFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingSearchService {

    private final ElasticSearchService elasticSearchService;




    public List<Listing> processFilters(ListingFilterRequest filterRequest) {
        List<Query> queryList = new ArrayList<>();

        if (filterRequest.getFirmId() != null) {
            queryList.add(QueryBuildersFactory.matchQuery("firmId", String.valueOf(filterRequest.getFirmId())));
        }

        if (filterRequest.getMin() != null && filterRequest.getMax() != null) {
            queryList.add(QueryBuildersFactory.rangeQuery("price", filterRequest.getMin(), filterRequest.getMax()));
        } else if (filterRequest.getMin() != null) {
            queryList.add(QueryBuildersFactory.rangeQuery("price", filterRequest.getMin(), null));
        } else if (filterRequest.getMax() != null) {
            queryList.add(QueryBuildersFactory.rangeQuery("price", null, filterRequest.getMax()));
        }
        if (filterRequest.getStatusId() != null) {
            queryList.add(QueryBuildersFactory.matchQuery("statusId", String.valueOf(filterRequest.getStatusId())));
        }
        if (filterRequest.getCategoryId() != null) {
            queryList.add(QueryBuildersFactory.matchQuery("categoryId", String.valueOf(filterRequest.getCategoryId())));

        }

        if (filterRequest.getCreatedDate() != null) {
            queryList.add(QueryBuildersFactory.matchQuery("createdAt", filterRequest.getCreatedDate().toString()));

        }

        if (filterRequest.getUpdatedDate() != null) {
            queryList.add(QueryBuildersFactory.matchQuery("updatedAt", filterRequest.getUpdatedDate().toString()));

        }
        if (!queryList.isEmpty()) {
            queryList.add(QueryBuildersFactory.rangeQuery("updatedAt", "2025-01-01", "2025-01-04"));
            BoolQuery boolQuery = QueryBuildersFactory.buildBoolQuery(queryList);
            return elasticSearchService.executeQuery(boolQuery);
        }
        return List.of();

    }

}
