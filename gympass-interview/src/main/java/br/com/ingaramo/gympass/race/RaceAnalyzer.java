package br.com.ingaramo.gympass.race;

import br.com.ingaramo.gympass.pilot.Pilot;
import br.com.ingaramo.gympass.pilot.PilotResult;
import br.com.ingaramo.gympass.util.TimeUtil;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class RaceAnalyzer {
    private static final Integer LAST_LAP = 4;

    public RaceResults analyze(final List<RaceLap> laps) {
        final Set<RaceLap> finishLaps = getFinishLaps(laps);
        final Set<RaceLap> raceLaps = filterSamePilotLapsByMaxLap(finishLaps);
        final AtomicInteger position = new AtomicInteger(0);
        final List<PilotResult> pilotResults = new ArrayList<>();

        raceLaps.stream().sorted(Comparator.comparing(RaceLap::getTime))
            .forEach(lap -> {
                PilotResult pilotResult = new PilotResult();
                pilotResult.setCompletedLaps(lap.getLap());
                pilotResult.setPilot(lap.getPilot());
                pilotResult.setPosition(position.incrementAndGet());
                pilotResult.setTotalRaceTime(getTotalRaceTimeForPilot(lap.getPilot(), laps));
                pilotResults.add(pilotResult);
            });

        return new RaceResults(pilotResults);
    }

    private LocalTime getTotalRaceTimeForPilot(final Pilot pilot, final List<RaceLap> laps) {
        return laps.stream()
            .filter(lap -> lap.getPilot().equals(pilot))
            .map(lap -> lap.getLapTime())
            .reduce(LocalTime.MIN, (last, next) -> TimeUtil.plusTime(last, next));
    }

    private Set<RaceLap> filterSamePilotLapsByMaxLap(final Set<RaceLap> finishLaps) {
        final Set<RaceLap> filteredLaps  = new HashSet<>();

        for (RaceLap lap: finishLaps) {
            final Pilot pilot = lap.getPilot();

            final List<RaceLap> samePilotEntries = finishLaps.stream()
                .filter(filterLap -> pilot.equals(filterLap.getPilot()))
                .collect(Collectors.toList());

            if (samePilotEntries.isEmpty()) {
                continue;
            }

            final RaceLap raceLap = samePilotEntries.stream()
                .max(Comparator.comparing(RaceLap::getLap))
                .orElse(null);

            filteredLaps.add(raceLap);
        }

        return filteredLaps;
    }

    private Set<RaceLap> getFinishLaps(final List<RaceLap> laps) {
        final AtomicBoolean raceFinished = new AtomicBoolean(false);
        final Set<RaceLap> finishLaps = new HashSet<>();
        for (RaceLap lap : laps) {
            if (raceFinished.get()) {
                finishLaps.add(lap);
                continue;
            }

            if (raceFinished.get() == false && LAST_LAP.equals(lap.getLap())) {
                raceFinished.set(true);
                finishLaps.add(lap);
                continue;
            }
        }
        return finishLaps;
    }

    private long getPilotCount(List<RaceLap> laps) {
        return laps.stream()
                .map(lap -> lap.getPilot())
                .distinct()
                .count();
    }
}
