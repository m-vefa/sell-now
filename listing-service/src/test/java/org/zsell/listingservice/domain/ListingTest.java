package org.zsell.listingservice.domain;

import org.junit.jupiter.api.Test;
import org.zsell.listingservice.enums.ListingStatus;

import static org.junit.jupiter.api.Assertions.*;

class ListingTest {


    @Test
    void shouldSetAndRetrieveAllListingPropertiesCorrectly() {
        Listing listing = new Listing();

        String title = "Beautiful House";
        listing.setTitle(title);
        assertEquals(title, listing.getTitle());


        listing.setActivateId(ListingStatus.ACTIVE.getId());
        assertEquals(listing.getActivateId(),111002);
    }

}