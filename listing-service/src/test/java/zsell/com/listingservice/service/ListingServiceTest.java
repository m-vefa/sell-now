package zsell.com.listingservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zsell.com.listingservice.converter.ListingConverter;
import zsell.com.listingservice.converter.ListingResponseConverter;
import zsell.com.listingservice.domain.Listing;
import zsell.com.listingservice.model.ListingResponse;
import zsell.com.listingservice.model.create.ListingCreateRequest;
import zsell.com.listingservice.model.create.ListingCreateResponse;
import zsell.com.listingservice.repository.ListingRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ListingServiceTest {
    @Mock
    private ListingRepository listingRepository;
    @Mock
    private ListingResponseConverter listingResponseConverter;
    @Mock
    private ListingConverter listingConverter;
    @InjectMocks
    private ListingService listingService;


    @Test
    void throwException_whenGetAllListingsByFirmId_withNullFirmId(){
        Throwable throwable = catchThrowable(() -> listingService.getListingsByFirmId(null));
        assertThat(throwable).isInstanceOf(Exception.class);
    }
    @Test
    void returnListing_whenGetAllListingsByFirmId_withValidFirmId(){
        Integer validFirmId=111;
        Listing foundListing1 = Listing.builder().listingId(4).build();
        Listing foundListing2 = Listing.builder().listingId(5).build();
        List<Listing> listings = Arrays.asList(foundListing1,foundListing2);
        ListingResponse listingResponse1= ListingResponse.builder().listingId(4).build();
        ListingResponse listingResponse2= ListingResponse.builder().listingId(5).build();
        List<ListingResponse> listingResponseList=Arrays.asList(listingResponse1,listingResponse2);

        when(listingRepository.findByFirmId(validFirmId)).thenReturn(listings);
        when(listingResponseConverter.applyToList(listings)).thenReturn(listingResponseList);
        List<ListingResponse> actualResponses = listingService.getListingsByFirmId(validFirmId);

        assertThat(actualResponses).hasSize(2);
        assertThat(actualResponses.get(0).getListingId()).isEqualTo(4);
        assertThat(actualResponses.get(1).getListingId()).isEqualTo(5);

        verify(listingRepository,times(1)).findByFirmId(validFirmId);
        verify(listingResponseConverter,times(1)).applyToList(listings);

    }
    @Test
    void returnListingId_whenCreateListing_withValidFirmId(){
        Integer listingId=111;
        Listing listing = Listing.builder().listingId(listingId).build();
        ListingCreateRequest listingCreateRequest = ListingCreateRequest.builder().title("title1").description("description").price(11).categoryId(1).build();

        when(listingConverter.convert(listingCreateRequest)).thenReturn(listing);

        ListingCreateResponse actualResponse = listingService.createListing(listingCreateRequest);

        assertThat(listingId).isEqualTo( actualResponse.getListingId());

        verify(listingConverter,times(1)).convert(listingCreateRequest);
        verify(listingRepository,times(1)).save(listing);

    }

}