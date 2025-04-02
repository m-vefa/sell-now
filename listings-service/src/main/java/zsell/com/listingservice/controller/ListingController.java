package zsell.com.listingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zsell.com.listingservice.domain.Listing;
import zsell.com.listingservice.model.ListingResponse;
import zsell.com.listingservice.model.create.ListingCreateRequest;
import zsell.com.listingservice.model.create.ListingCreateResponse;
import zsell.com.listingservice.service.ListingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;

    @GetMapping
    public List<ListingResponse> getListingsByFirmId(@RequestParam Integer firmId) {
        return listingService.getListingsByFirmId(firmId);
    }
    @PostMapping
    public ListingCreateResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest) {
        return listingService.createListing(listingCreateRequest);
    }
    @PostMapping("/{listingId}/publish")
    public void publishListing(@PathVariable Integer listingId) {
        listingService.publishListing(listingId);
    }

    @GetMapping("/{listingId}")
    public Optional<Listing> getListing(@PathVariable Integer listingId) {
        return listingService.getListing(listingId);
    }
}
