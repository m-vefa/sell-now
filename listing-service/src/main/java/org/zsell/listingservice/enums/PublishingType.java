package org.zsell.listingservice.enums;

public enum PublishingType {
    FOR_SALE(1000),
    FOR_RENT(2000);
    private final int publishingId;

    PublishingType(int publishingId) {
        this.publishingId = publishingId;
    }
}
