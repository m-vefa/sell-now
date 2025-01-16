package zsell.com.searchservice.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zsell.com.searchservice.client.ListingServiceClient;
import zsell.com.searchservice.configuration.queue.ListingChangedQueueConfiguration;
import zsell.com.searchservice.converter.ListingConverter;
import zsell.com.searchservice.service.ListingSearchService;

@Component
@RequiredArgsConstructor
public class ListingCreateListener {
    private final ListingServiceClient listingServiceClient;
    private final ListingSearchService listingSearchService;
    private final ListingConverter listingConverter;

    @RabbitListener(queues = ListingChangedQueueConfiguration.QUEUE )
    public void saveListingLead(Integer listingId) {
        System.out.println("listingId: "+listingId);
        System.out.println("saveListing: "+listingId);
       // Listing listing = listingServiceClient.findListing(listingId);
        //listingSearchService.saveListing();
    }

}