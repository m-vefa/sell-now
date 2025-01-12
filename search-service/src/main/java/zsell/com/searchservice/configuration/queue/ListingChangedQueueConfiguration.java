package zsell.com.searchservice.configuration.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ListingChangedQueueConfiguration {

    public static final String QUEUE = "zsell.queue.listing-service";
    private static final String DEAD_LETTER_QUEUE = "zsell.listing-service.save.dead-letter"; // Ensure this is correct

    @Bean
    public Queue listingChangedQueue() {
        return new Queue(QUEUE, true); // Durable queue
    }
}