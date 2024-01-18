package com.changejar.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class LocalDateTimeUtils {

    public static LocalDateTime getLocalDateTime(Long fromMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(fromMillis), TimeZone.getTimeZone("Asia/Kolkata").toZoneId());
    }

}
