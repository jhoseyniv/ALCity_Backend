package com.alcity.utility;

import java.time.*;
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
 public static String getNow(){
     LocalDateTime current = LocalDateTime.now();
     DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     return current.format(format);

 }
    public static Integer calculateAge(Integer birthYear) {
        LocalDate birthdate = LocalDate.of(birthYear, 12, 29);

        // Calculate period between birthdate and current date
        Period period = Period.between(birthdate, LocalDate.now());

        return period.getYears();
    }
}
