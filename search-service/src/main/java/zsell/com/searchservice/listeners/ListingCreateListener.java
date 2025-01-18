package zsell.com.searchservice.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zsell.com.searchservice.client.ListingServiceClient;
import zsell.com.searchservice.configuration.queue.ListingChangedQueueConfiguration;
import zsell.com.searchservice.converter.ListingConverter;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.service.ListingSaveService;

@Component
@RequiredArgsConstructor
public class ListingCreateListener {
    private final ListingServiceClient listingServiceClient;
    private final ListingSaveService listingSaveService;
    private final ListingConverter listingConverter;

    @RabbitListener(queues = ListingChangedQueueConfiguration.QUEUE )
    public void saveListingLead(Integer listingId) {
        Listing listing = listingConverter.apply(listingServiceClient.getListing(listingId));
        listingSaveService.saveListing(listing);
    }

}