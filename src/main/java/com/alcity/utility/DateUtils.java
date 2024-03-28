package com.alcity.utility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class  DateUtils {
    public static String getDatatimeFromLong(long m){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("uuuu-MM-dd 'Time' HH:mm:ssXXX")
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']')
                .toFormatter(Locale.ENGLISH);
            ZoneId zoneId = ZoneId.systemDefault();

            ZonedDateTime zdtFromEpochSeconds = Instant.ofEpochSecond(m).atZone(zoneId);

        return zdtFromEpochSeconds.format(formatter);
    }
}
