package zsell.com.listingservice.configuration.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ListingPublishExchangeConfiguration {
    public static final String ZSELL_LISTING_EXCHANGE = "zsell.exchange.listing-service";

    private final RabbitAdmin rabbitAdmin;

    @Bean
    public FanoutExchange listingPublishExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange(ZSELL_LISTING_EXCHANGE, true, false);
        fanoutExchange.setAdminsThatShouldDeclare(rabbitAdmin);
        return fanoutExchange;
    }

}
