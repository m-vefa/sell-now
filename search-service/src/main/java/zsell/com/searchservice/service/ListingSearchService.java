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

import static zsell.com.searchservice.constants.ListingIndexProperties.CATEGORY_ID;
import static zsell.com.searchservice.constants.ListingIndexProperties.CREATED_AT;
import static zsell.com.searchservice.constants.ListingIndexProperties.FIRM_ID;
import static zsell.com.searchservice.constants.ListingIndexProperties.PRICE;
import static zsell.com.searchservice.constants.ListingIndexProperties.STATUS_ID;
import static zsell.com.searchservice.constants.ListingIndexProperties.UPDATED_AT;

@Service
@RequiredArgsConstructor
public class ListingSearchService {

    private final ElasticSearchService elasticSearchService;
    public List<Listing> processFilters(ListingFilterRequest filterRequest) {
        List<Query> queryList = new ArrayList<>();

        if (filterRequest.getFirmId() != null) {
            queryList.add(QueryBuildersFactory.matchQuery(FIRM_ID, String.valueOf(filterRequest.getFirmId())));
        }

        if (filterRequest.getMin() != null && filterRequest.getMax() != null) {
            queryList.add(QueryBuildersFactory.rangeQuery(PRICE, filterRequest.getMin(), filterRequest.getMax()));
        } else if (filterRequest.getMin() != null) {
            queryList.add(QueryBuildersFactory.rangeQuery(PRICE, filterRequest.getMin(), null));
        } else if (filterRequest.getMax() != null) {
            queryList.add(QueryBuildersFactory.rangeQuery(PRICE, null, filterRequest.getMax()));
        }
        if (filterRequest.getStatusId() != null) {
            queryList.add(QueryBuildersFactory.matchQuery(STATUS_ID, String.valueOf(filterRequest.getStatusId())));
        }
        if (filterRequest.getCategoryId() != null) {
            queryList.add(QueryBuildersFactory.matchQuery(CATEGORY_ID, String.valueOf(filterRequest.getCategoryId())));

        }

        if (filterRequest.getCreatedDate() != null) {
            queryList.add(QueryBuildersFactory.matchQuery(CREATED_AT, filterRequest.getCreatedDate().toString()));

        }

        if (filterRequest.getUpdatedDate() != null) {
            queryList.add(QueryBuildersFactory.matchQuery(UPDATED_AT, filterRequest.getUpdatedDate().toString()));

        }
        if (!queryList.isEmpty()) {
            BoolQuery boolQuery = QueryBuildersFactory.buildBoolQuery(queryList);
            return elasticSearchService.executeQuery(boolQuery);
        }
        return List.of();

    }

}
