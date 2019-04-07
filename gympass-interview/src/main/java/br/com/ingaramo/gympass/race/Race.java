package br.com.ingaramo.gympass.race;

import java.util.List;
import java.util.Objects;

public class Race {
    private List<RaceLap> raceLaps;

    public List<RaceLap> getRaceLaps() {
        return raceLaps;
    }

    public RaceResults getRaceResults() {
        if (raceLaps == null || raceLaps.isEmpty()) {
            throw new IllegalArgumentException("Race not provided");
        }

        return new RaceAnalyzer().analyze(raceLaps);
    }

    Race(List<RaceLap> race) {
        this.raceLaps = race;
    }

    Race() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race1 = (Race) o;
        return Objects.equals(raceLaps, race1.raceLaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceLaps);
    }

    @Override
    public String toString() {
        return "Race{" +
            "raceLaps=" + raceLaps +
            '}';
    }
}
