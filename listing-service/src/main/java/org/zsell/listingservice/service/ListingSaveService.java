package org.zsell.listingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.enums.ListingStatus;
import org.zsell.listingservice.exception.ListingAlreadyPublishedException;
import org.zsell.listingservice.exception.ListingPublishException;
import org.zsell.listingservice.repository.ListingRepository;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListingSaveService {
    private final ListingRepository listingsRepository;

    public void activateListingById(Listing listing, Integer listingId) {

        Set<Integer> validStatuses = Set.of(ListingStatus.INACTIVE.getId(), ListingStatus.DRAFT.getId(), ListingStatus.EXPIRED.getId());
        if (Objects.equals(listing.getActivateId(), ListingStatus.ACTIVE.getId())) {
            throw new ListingAlreadyPublishedException(String.valueOf(listingId));
        }

        if (validStatuses.contains(listing.getActivateId())) {
            listing.setActivateId(ListingStatus.ACTIVE.getId());
            listing.setUpdatedDate(new Date());
            listingsRepository.save(listing);
        } else {
            String activateIdString = Optional.ofNullable(listing.getActivateId()).map(Object::toString).orElse("null");
            throw new ListingPublishException(listing.getListingId().toString(), activateIdString);

        }

    }
}