package br.com.ingaramo.gympass.race;

import br.com.ingaramo.gympass.pilot.Pilot;
import br.com.ingaramo.gympass.util.TimeUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

class RaceLineAdapter {
    private static final String HOUR_FORMAT = "HH:mm:ss.SSS";
    private static final String HOUR = "00:0";
    private static final String NUMBER_FORMAT = "##,000";
    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DECIMAL_FORMAT = new DecimalFormat(NUMBER_FORMAT, symbols);
        DECIMAL_FORMAT.setParseBigDecimal(true);
    }

    RaceLap adaptRaceLapFromLine(final List<String> line) {
        if (line == null || line.size() != 5) {
            throw new IllegalArgumentException("Wrong line provided to adapter");
        }

        final LocalTime time = TimeUtil.toTime(line.get(0), HOUR_FORMAT);
        final String pilot = line.get(1);
        final Integer lap = Integer.valueOf(line.get(2));
        final LocalTime lapTime = TimeUtil.toTime(HOUR + line.get(3), HOUR_FORMAT);

        final BigDecimal avgLapTime;
        try {
            avgLapTime = (BigDecimal) DECIMAL_FORMAT.parse(line.get(4));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        RaceLap raceLap = new RaceLap();
        raceLap.setAvgLapTime(avgLapTime);
        raceLap.setLapTime(lapTime);
        raceLap.setLap(lap);
        raceLap.setPilot(new Pilot(pilot));
        raceLap.setTime(time);

        return raceLap;
    }
}
