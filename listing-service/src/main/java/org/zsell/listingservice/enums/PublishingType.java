package org.zsell.listingservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PublishingType {
    FOR_SALE(1000),
    FOR_RENT(2000);
    private final Integer publishingId;

}
