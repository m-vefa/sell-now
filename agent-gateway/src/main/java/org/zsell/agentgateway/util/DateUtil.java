package org.zsell.agentgateway.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Builder
public class DateUtil {

    @Builder.Default
    private final Duration expirationDuration = Duration.ofDays(30);

    @Builder.Default
    private final ZoneId zoneId = ZoneId.systemDefault();

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now(zoneId);
    }

    public LocalDateTime convertDateToLocalDateTime(@NonNull Date date) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public Instant calculateExpiration(@NonNull Instant issuedAt) {
        return issuedAt.plus(expirationDuration);
    }

    public Date convertLocalDateTimeToDate(@NonNull LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }
}
