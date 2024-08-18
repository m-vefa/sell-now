package org.zsell.listingservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConversionEventType {
    FAVORITING(19004),
    MESSAGE_BUTTON_CLICK(19002),
    PHONE_VISIBILITY(19001);

    private final Integer value;

}