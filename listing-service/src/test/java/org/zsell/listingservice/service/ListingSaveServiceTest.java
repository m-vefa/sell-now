package org.zsell.listingservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.enums.ListingStatus;
import org.zsell.listingservice.exception.ListingAlreadyPublishedException;
import org.zsell.listingservice.exception.ListingPublishException;
import org.zsell.listingservice.repository.ListingRepository;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListingSaveServiceTest {
    @Mock
    private ListingRepository listingsRepository;
    @InjectMocks
    private ListingSaveService listingSaveService;

    @Test
    void throwAlreadyPublishedException_whenActivateListingById_withAlreadyPublishedListing() {
        Integer listingId = 4;
        Listing listing = Listing.builder().listingId(listingId).activateId(ListingStatus.ACTIVE.getId()).build();

        Throwable throwable = catchThrowable(() -> listingSaveService.activateListingById(listing, listingId));

        assertThat(throwable).isInstanceOf(ListingAlreadyPublishedException.class);
        final ListingAlreadyPublishedException exception = (ListingAlreadyPublishedException) throwable;
        assertThat(exception.getKey()).isEqualTo("ListingAlreadyPublishedException");
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.IM_USED);
    }
    @Test
    void throwListingPublishException_whenActivateListingById_withNotValidActivateId(){
        Integer listingId = 4;
        Listing listing = Listing.builder().listingId(listingId).activateId(1111).build();
        Throwable throwable = catchThrowable(()->listingSaveService.activateListingById(listing,listingId));
        final ListingPublishException exception =(ListingPublishException) throwable;
        assertThat(exception.getKey()).isEqualTo("ListingPublishException");
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    void publishSuccessfully_whenActivateListingById_withValidListing() {
        //given
        Integer listingId = 4;
        Listing listing = Listing.builder().listingId(listingId).activateId(ListingStatus.INACTIVE.getId()).build();
        //when
        listingSaveService.activateListingById(listing,listingId);
        //then
        ArgumentCaptor<Listing> captor = ArgumentCaptor.forClass(Listing.class);
        verify(listingsRepository, times(1)).save(captor.capture());
        Listing savedListing = captor.getValue();
        assertEquals(ListingStatus.ACTIVE.getId(), savedListing.getActivateId());
        assertNotNull(savedListing.getUpdatedDate());
        assertTrue(savedListing.getUpdatedDate().getTime() <= new Date().getTime());

    }
}