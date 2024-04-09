package org.zsell.listingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zsell.listingservice.model.ListingCreateRequest;
import org.zsell.listingservice.model.ListingResponse;
import org.zsell.listingservice.service.ListingService;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping
    public List<ListingResponse> getAllListing() {
        return listingService.getAllListing();
    }

    @GetMapping("/{id}")
    public ListingResponse getListingById(@PathVariable Integer id) {
        return listingService.getListingById(id);
    }

    @PostMapping
    public ListingResponse createListing(@RequestBody ListingCreateRequest listingCreateRequest) {
        return listingService.createListing(listingCreateRequest);
    }

    @PutMapping("/{id}")
    public ListingResponse updateListing(@PathVariable Integer id, @RequestBody ListingCreateRequest listingCreateRequest) {
        return listingService.updateListing(id,listingCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable Integer id) {
        listingService.deleteListing(id);
    }
}
