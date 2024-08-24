package org.zsell.listingservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.zsell.listingservice.converter.PublishListingResponseConverter;
import org.zsell.listingservice.domain.Listing;
import org.zsell.listingservice.enums.ListingStatus;
import org.zsell.listingservice.exception.ListingNotFoundException;
import org.zsell.listingservice.model.ListingPublishResponse;
import org.zsell.listingservice.repository.ListingRepository;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ListingServiceTest {
    @Mock
    private ListingRepository listingsRepository;
    @Mock
    private PublishListingResponseConverter publishListingResponseConverter;
    @InjectMocks
    private ListingService listingService;


    @Test
    void throwException_whenPublishListing_withNonExistListingId() {
        //given
        Integer listingId = null;

        //when
        Throwable throwable = catchThrowable(() -> listingService.publishListing(listingId));

        //then
        assertThat(throwable).isInstanceOf(ListingNotFoundException.class);
        final ListingNotFoundException exception = (ListingNotFoundException) throwable;
        assertThat(exception.getKey()).isEqualTo("ListingNotFoundException");
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void returnPublishResponse_whenPublishListing_withExistListingId() {
        //given
        Listing foundListing = Listing.builder()
                .listingId(4)
                .activateId(ListingStatus.INACTIVE.getId())
                .build();
        ListingPublishResponse listingPublishResponse = ListingPublishResponse.builder()
                .id(4)
                .activateId(ListingStatus.ACTIVE.getId())
                .build();

        when(listingsRepository.findById(4)).thenReturn(Optional.ofNullable(foundListing));
        when(publishListingResponseConverter.apply(Objects.requireNonNull(foundListing))).thenReturn(listingPublishResponse);

        //when
        listingService.publishListing(4);

        //then
        assertThat(listingPublishResponse.getId()).isEqualTo(foundListing.getListingId());
        assertThat(listingPublishResponse.getActivateId()).isEqualTo(ListingStatus.ACTIVE.getId());
    }

}
