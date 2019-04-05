package br.com.ingaramo.gympass.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static LocalTime toTime(final String time, final String pattern) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }
}
