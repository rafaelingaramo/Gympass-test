package br.com.ingaramo.gympass.race;

import java.util.List;
import java.util.Objects;

public class Race {
    private List<RaceLap> race;

    public List<RaceLap> getRace() {
        return race;
    }

    public void setRace(List<RaceLap> race) {
        this.race = race;
    }

    public RaceResults getRaceResults() {
        if (race == null || race.isEmpty()) {
            throw new IllegalArgumentException("Race not provided");
        }

        return new RaceAnalyzer().analyze(race);
    }

    public Race(List<RaceLap> race) {
        this.race = race;
    }

    public Race() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race1 = (Race) o;
        return Objects.equals(race, race1.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(race);
    }

    @Override
    public String toString() {
        return "Race{" +
            "race=" + race +
            '}';
    }
}
