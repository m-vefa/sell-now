package zsell.com.searchservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zsell.com.searchservice.domain.Listing;
import zsell.com.searchservice.domain.ListingFilterRequest;
import zsell.com.searchservice.service.ListingSearchService;

import java.util.List;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingSearchService listingSearchService;
    @PostMapping("/search")
    public List<Listing> getFilteredListings(@RequestBody ListingFilterRequest filterRequest) {
        return listingSearchService.processFilters(filterRequest);
    }
}
