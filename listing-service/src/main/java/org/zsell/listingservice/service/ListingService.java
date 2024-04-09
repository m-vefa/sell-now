package org.zsell.listingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsell.listingservice.converter.ListingResponseConverter;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.model.ListingCreateRequest;
import org.zsell.listingservice.model.ListingResponse;
import org.zsell.listingservice.repository.ListingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingsRepository;
    private final ListingResponseConverter listingResponseConverter;

    public List<ListingResponse> getAllListing() {
        List<Listing> listings = listingsRepository.findAll();

        return listings.stream().map(listingResponseConverter::convert).collect(Collectors.toList());
    }

    public ListingResponse getListingById(Integer id) {
        Optional<Listing> listingOptional = listingsRepository.findById(id);


        return listingOptional.map(listingResponseConverter::convert).orElse(null);

    }

    public ListingResponse createListing(ListingCreateRequest listingCreateRequest) {
        Listing listing = Listing.builder()
                .title(listingCreateRequest.getTitle())
                .description(listingCreateRequest.getDescription())
                .price(listingCreateRequest.getPrice())
                .build();
        listingsRepository.save(listing);
        return listingResponseConverter.convert(listing);
    }

    public ListingResponse updateListing(Integer id, ListingCreateRequest listingCreateRequest) {

        Optional<Listing> existingListing = listingsRepository.findById(id);
        if (existingListing.isPresent()) {
            Listing listing = Listing.builder()
                    .title(listingCreateRequest.getTitle())
                    .description(listingCreateRequest.getDescription())
                    .price(listingCreateRequest.getPrice())
                    .build();
            listingsRepository.save(listing);
            return listingResponseConverter.convert(listing);
        }
        return null;
    }

    public void deleteListing(Integer id) {
        listingsRepository.deleteById(id);
    }
}
