package zsell.com.searchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import zsell.com.searchservice.configuration.feign.listing.ListingApiClientFeignConfiguration;
import zsell.com.searchservice.model.ListingCreateRequest;
import zsell.com.searchservice.model.ListingResponse;

@FeignClient(value = "ListingServiceClient", url = "${listing.service.url}",configuration = ListingApiClientFeignConfiguration.class)
public interface ListingServiceClient {
    @PostMapping(value = ("/listings"))
    ListingResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest);
}


