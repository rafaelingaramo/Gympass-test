package br.com.ingaramo.gympass.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private TimeUtil() {

    }

    public static LocalTime toTime(final String time, final String pattern) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime plusTime(final LocalTime first, final LocalTime second) {
        final LocalTime localTime = LocalTime.of(second.getHour(),
            second.getMinute(),
            second.getSecond(),
            second.getNano());

        final LocalTime resultTime = localTime.plusHours(first.getHour())
            .plusMinutes(first.getMinute())
            .plusSeconds(first.getSecond())
            .plusNanos(first.getNano());
        return resultTime;
    }
}
