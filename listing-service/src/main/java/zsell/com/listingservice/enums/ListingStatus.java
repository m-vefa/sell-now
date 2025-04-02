package zsell.com.listingservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ListingStatus {

    DRAFT(111001, "Taslak"),
    ACTIVE(111002, "Aktif"),
    INACTIVE(111003, "Pasif"),
    EXPIRED(111004, "Süresi Dolmuş"),
    DELETED(111005, "Silindi"),
    REJECTED(111006, "Reddedildi"),
    PENDING_APPROVAL(111007, "Onay Bekliyor"),
    SUSPENDED(111008, "Askıya Alındı");

    private final Integer id;
    private final String description;
}
