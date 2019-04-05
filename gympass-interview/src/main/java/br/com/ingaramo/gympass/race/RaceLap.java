package br.com.ingaramo.gympass.race;

import br.com.ingaramo.gympass.pilot.Pilot;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

public class RaceLap {
    private LocalTime time;
    private Pilot pilot;
    private Integer lap;
    private LocalTime lapTime;
    private BigDecimal avgLapTime;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Integer getLap() {
        return lap;
    }

    public void setLap(Integer lap) {
        this.lap = lap;
    }

    public LocalTime getLapTime() {
        return lapTime;
    }

    public void setLapTime(LocalTime lapTime) {
        this.lapTime = lapTime;
    }

    public BigDecimal getAvgLapTime() {
        return avgLapTime;
    }

    public void setAvgLapTime(BigDecimal avgLapTime) {
        this.avgLapTime = avgLapTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceLap raceLap = (RaceLap) o;
        return Objects.equals(time, raceLap.time) &&
            Objects.equals(pilot, raceLap.pilot) &&
            Objects.equals(lap, raceLap.lap) &&
            Objects.equals(lapTime, raceLap.lapTime) &&
            Objects.equals(avgLapTime, raceLap.avgLapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, pilot, lap, lapTime, avgLapTime);
    }

    @Override
    public String toString() {
        return "RaceLap{" +
            "time=" + time +
            ", pilot='" + pilot + '\'' +
            ", lap=" + lap +
            ", lapTime=" + lapTime +
            ", avgLapTime=" + avgLapTime +
            '}';
    }
}
