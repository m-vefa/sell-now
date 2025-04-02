package zsell.com.listingservice.service.queue;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import zsell.com.listingservice.configuration.queue.ListingPublishExchangeConfiguration;
import zsell.com.listingservice.model.ListingChangePayload;

@Service
@RequiredArgsConstructor
public class ListingChangedQueueService {
    private final RabbitTemplate rabbitTemplate;
    public void enqueueListingChange(Integer listingId ) {
        ListingChangePayload listingChangePayload = ListingChangePayload.builder()
                .listingId(listingId).build();
        rabbitTemplate.convertAndSend(ListingPublishExchangeConfiguration.ZSELL_LISTING_EXCHANGE, "", listingChangePayload);
    }
}
