package com.alcity.utility;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    public static String getDate(LocalDateTime  localDateTime){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDateTime.format(format);
    }

    public static String getNowString(LocalDateTime current ){
       // LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return current.format(format);
    }

    public static Integer calculateAgeFromGregorian(Integer birthYear) {
        LocalDate birthdate = LocalDate.of(birthYear, 12, 29);
        // Calculate period between birthdate and current date
        Period period = Period.between(birthdate, LocalDate.now());

        return period.getYears();
    }

    public static Integer calculateAgeFromJalali(Integer birthYear) {
        JalaliCalendar jalaliCalendar = new JalaliCalendar(birthYear, 12, 29);
        GregorianCalendar gc = jalaliCalendar.toGregorian();
        return calculateAgeFromGregorian(gc.get(Calendar.YEAR));
    }

        public static void main(String[] args)  {
        System.out.println("this is a get tumbnile of images.....");
        System.out.println();
        }

}
