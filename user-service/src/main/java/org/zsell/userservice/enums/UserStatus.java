package org.zsell.userservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    PENDING_APPROVAL(1001, "Onay Bekliyor"),
    ACTIVE(1002, "Aktif"),
    INACTIVE(1003, "Pasif"),
    DELETED(1004, "Silindi"),
    SUSPENDED(1005, "Askıya Alındı");

    private final Integer id;
    private final String description;
}

