package br.com.ingaramo.gympass.pilot;

import java.time.LocalTime;
import java.util.Objects;

public class PilotResult {
    private Integer position;
    private Pilot pilot;
    private Integer completedLaps;
    private LocalTime totalRaceTime;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Integer getCompletedLaps() {
        return completedLaps;
    }

    public void setCompletedLaps(Integer completedLaps) {
        this.completedLaps = completedLaps;
    }

    public LocalTime getTotalRaceTime() {
        return totalRaceTime;
    }

    public void setTotalRaceTime(LocalTime totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilotResult that = (PilotResult) o;
        return Objects.equals(position, that.position) &&
            Objects.equals(pilot, that.pilot) &&
            Objects.equals(completedLaps, that.completedLaps) &&
            Objects.equals(totalRaceTime, that.totalRaceTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, pilot, completedLaps, totalRaceTime);
    }

    @Override
    public String toString() {
        return "PilotResult{" +
            "position=" + position +
            ", pilot=" + pilot +
            ", completedLaps=" + completedLaps +
            ", totalRaceTime=" + totalRaceTime +
            '}';
    }
}
