package org.zsell.listingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zsell.listingservice.model.ListingCreateRequest;
import org.zsell.listingservice.model.ListingPublishResponse;
import org.zsell.listingservice.model.ListingResponse;
import org.zsell.listingservice.service.ListingService;

import java.util.List;
@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

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

    @PostMapping("/publish/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ListingPublishResponse publishListing(@PathVariable int id) {
        return listingService.publishListing(id);
    }

}
