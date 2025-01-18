package zsell.com.searchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zsell.com.searchservice.configuration.feign.listing.ListingApiClientFeignConfiguration;

import java.util.Map;

@FeignClient(value = "ListingServiceClient", url = "${listing.service.url}",configuration = ListingApiClientFeignConfiguration.class)
public interface ListingServiceClient {
    @GetMapping("/listing/{listingId}")
    Map<String ,Object>getListing(@PathVariable Integer listingId);
}


