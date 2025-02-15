package zsell.com.searchservice.configuration.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ListingChangedQueueConfiguration {
    public static final String ZSELL_LISTING_EXCHANGE = "zsell.exchange.listing-service";

    public static final String QUEUE = "zsell.queue.listing-service";
    private static final String DEAD_LETTER_QUEUE = "zsell.queue.listing-service.dead-letter";


    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    private final RabbitAdmin rabbitAdmin;

    @Bean
    public FanoutExchange listingChangedExchange() {
        return new FanoutExchange(ZSELL_LISTING_EXCHANGE);
    }
    @Bean
    public Queue listingChangedQueue() {
        final Queue queue = QueueBuilder.durable(QUEUE)
                .withArgument(X_DEAD_LETTER_EXCHANGE, "")
                .withArgument(X_DEAD_LETTER_ROUTING_KEY, DEAD_LETTER_QUEUE)
                .build();
        queue.setAdminsThatShouldDeclare(rabbitAdmin);
        return queue;
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding binding(Queue listingChangedQueue, FanoutExchange listingChangedExchange) {
        return BindingBuilder.bind(listingChangedQueue).to(listingChangedExchange);
    }


}



