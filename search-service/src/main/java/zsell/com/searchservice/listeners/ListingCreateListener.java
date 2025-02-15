package zsell.com.searchservice.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zsell.com.searchservice.configuration.queue.ListingChangedQueueConfiguration;
import zsell.com.searchservice.model.ListingIdDto;
import zsell.com.searchservice.service.ListingSaveService;

@Component
@RequiredArgsConstructor
public class ListingCreateListener {
    private final ListingSaveService listingSaveService;

    @RabbitListener(queues = ListingChangedQueueConfiguration.QUEUE )
    public void saveListingLead(ListingIdDto listingId) {
        listingSaveService.saveListing(listingId.getListingId());
    }

}