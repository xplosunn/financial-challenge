package com.github.xplosunn.financialchallenge.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DatabaseConversions {

    private DatabaseConversions() {}

    private static final ZoneId databaseZoneId = ZoneId.of("UTC");

    public static ZonedDateTime zonedDateTime(Timestamp sqlTimestamp) {
        return sqlTimestamp
                .toLocalDateTime()
                .atZone(databaseZoneId);
    }

    public static Timestamp timestampAtDayStart(LocalDate localDate) {
        return timestamp(localDate.atStartOfDay(databaseZoneId));
    }

    public static Timestamp timestamp(ZonedDateTime zonedDateTime) {
        return Timestamp.from(zonedDateTime.toInstant());
    }

}
