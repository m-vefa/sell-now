package zsell.com.searchservice.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.json.JsonData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryBuildersFactory {
    public static Query matchQuery(String field, String value) {
        return MatchQuery.of(m -> m
                .field(field)
                .query(value)
        )._toQuery();
    }

    public static Query rangeQuery(String field, String gte, String lte) {
        return RangeQuery.of(r -> r.untyped(n ->
                n.field(field)
                        .gte(gte != null ? JsonData.of(gte) : null)
                        .lte(lte != null ? JsonData.of(lte) : null)
        ))._toQuery();
    }

    public static BoolQuery buildBoolQuery(List<Query> queries) {
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        for (Query query : queries) {
            boolQueryBuilder.must(query);
        }
        return boolQueryBuilder.build();
    }
}

