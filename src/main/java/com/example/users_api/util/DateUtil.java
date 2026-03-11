package com.example.users_api.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getMadagascarTimestamp(){

        ZonedDateTime now = ZonedDateTime.now(
                ZoneId.of("Indian/Antananarivo")
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return now.format(formatter);
    }

}