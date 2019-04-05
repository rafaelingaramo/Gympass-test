package br.com.ingaramo.gympass.race;

import java.util.Objects;
import java.util.Set;

public class Race {
    private Set<RaceLap> race;

    public Set<RaceLap> getRace() {
        return race;
    }

    public void setRace(Set<RaceLap> race) {
        this.race = race;
    }

    public Race(Set<RaceLap> race) {
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