package zsell.com.listingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zsell.com.listingservice.converter.ListingConverter;
import zsell.com.listingservice.converter.ListingResponseConverter;
import zsell.com.listingservice.domain.Listing;
import zsell.com.listingservice.enums.ListingStatus;
import zsell.com.listingservice.exception.ListingNotFoundException;
import zsell.com.listingservice.model.ListingResponse;
import zsell.com.listingservice.model.create.ListingCreateRequest;
import zsell.com.listingservice.model.create.ListingCreateResponse;
import zsell.com.listingservice.repository.ListingRepository;
import zsell.com.listingservice.service.queue.ListingChangedQueueService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final ListingResponseConverter listingResponseConverter;
    private final ListingConverter listingConverter;
    private final ListingChangedQueueService listingChangedQueueService;

    public List<ListingResponse> getListingsByFirmId(Integer firmId) {
        if (firmId == null) {
            throw new IllegalArgumentException("Firm ID must not be null");
        }
       List<Listing> foundListings= listingRepository.findByFirmId(firmId);
        return listingResponseConverter.applyToList(foundListings);
    }
    public ListingCreateResponse createListing(ListingCreateRequest listingCreateRequest) {
        Listing listing = listingConverter.convert(listingCreateRequest);
        listingRepository.save(listing);
        return new ListingCreateResponse(listing.getListingId());
    }

    public void publishListing(Integer listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new ListingNotFoundException("Listing with ID " + listingId + " not found!"));
        if (Objects.nonNull(listing)) {
            listing.setStatusId(ListingStatus.ACTIVE.getId());
            listing.setUpdatedDate(LocalDateTime.now());
            listingRepository.save(listing);
            listingChangedQueueService.enqueueListingChange(listingId);
        }
    }

    public Optional<Listing> getListing(Integer listingId) {
        return listingRepository.findById(listingId);
    }
}
