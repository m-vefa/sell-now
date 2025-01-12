package zsell.com.searchservice.configuration.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchHealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchHealthCheck.class);

    @Bean
    public CommandLineRunner checkElasticsearchConnection(ElasticsearchClient elasticsearchClient) {
        return args -> {
            try {
                InfoResponse response = elasticsearchClient.info();
                logger.info("Connected to Elasticsearch cluster with name: {}", response.clusterName());
                logger.info("Elasticsearch version: {}", response.version().number());
            } catch (Exception e) {
                logger.error("Failed to connect to Elasticsearch", e);
            }
        };
    }
}
