package com.srs.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class FormatUtil {

    private static final DateTimeFormatter TIMESTAMP_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SX"))
            .toFormatter();

    public static LocalDateTime localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return LocalDateTime.parse(localDateTime.toString(), TIMESTAMP_PARSER);
    }
}
