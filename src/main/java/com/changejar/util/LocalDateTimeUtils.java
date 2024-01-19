package com.changejar.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Utility file to support conversion for milliseconds to/from LocalDateTime type.
 *
 * @author Abhishek Sharma
 */
public class LocalDateTimeUtils {

    /**
     * Given the time instance in milliseconds, convert it to LocalDateTime type.
     * @param fromMillis the time instance in milliseconds.
     * @return an instance of LocalDateTime.
     */
    public static LocalDateTime getLocalDateTime(Long fromMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(fromMillis), TimeZone.getTimeZone("Asia/Kolkata").toZoneId());
    }

}
