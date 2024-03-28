package com.alcity.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class TestUtitlty {

    public static void main(String args[]){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("uuuu-MM-dd 'Time' HH:mm:ssXXX")
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']')
                .toFormatter(Locale.ENGLISH);

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdtNow = ZonedDateTime.now(zoneId);
        System.out.println(zdtNow);
        long epochSecond = zdtNow.toEpochSecond();
        System.out.println(epochSecond);
        ZonedDateTime zdtFromEpochSeconds = Instant.ofEpochSecond(epochSecond).atZone(zoneId);
        System.out.println(zdtFromEpochSeconds.format(formatter));
    }
}
