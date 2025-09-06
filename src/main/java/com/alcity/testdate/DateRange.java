package com.alcity.testdate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateRange {
    public static void main(String[] args) {

        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.minusDays(7);

        System.out.println(String.format(
                "Now:  %s\nThen: %s",
                now.format(format),
                then.format(format)
        ));
        /*
            Example output:
                Now:  2014-05-09T14:51:48Z
                Then: 2014-05-02T14:51:48Z
         */
    }
}
