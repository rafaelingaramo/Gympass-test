package br.com.ingaramo.gympass.race;

import br.com.ingaramo.gympass.pilot.PilotResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class RaceProcessorTest {
    @Test(expected = NullPointerException.class)
    public void processRaceFromFileNullFile() throws IOException {
        new RaceProcessor().processRaceFromFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void processRaceFromFileEmptyFile() throws IOException {
        new RaceProcessor().processRaceFromFile("src/test/resources/empty.log");
    }

    @Test(expected = IllegalArgumentException.class)
    public void processRaceFromFileHeaderOnlyFile() throws IOException {
        final Race race = new RaceProcessor()
            .processRaceFromFile("src/test/resources/header-only.log");
        final List<RaceLap> raceLaps = race.getRace();
        Assert.assertTrue(raceLaps.isEmpty());

        try {
            race.getRaceResults();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Race not provided");
            throw e;
        }
    }

    @Test
    public void processRaceFromFileSingleLineFile() throws IOException {
        final Race race = new RaceProcessor()
            .processRaceFromFile("src/test/resources/single.log");
        final List<RaceLap> raceLaps = race.getRace();
        final RaceLap lap = raceLaps.get(0);
        Assert.assertEquals(raceLaps.size(), 1);
        Assert.assertEquals(lap.getTime().toString(), "23:49:08.277");
        Assert.assertEquals(lap.getPilot().getComposedCode(), "038 – F.MASSA");
        Assert.assertEquals(lap.getLap(), new Integer(1));
        Assert.assertEquals(lap.getLapTime().toString(), "00:01:02.852");
        Assert.assertEquals(lap.getAvgLapTime().toString(), "44.275");

        final RaceResults raceResults = race.getRaceResults();
        final List<PilotResult> pilotResults = raceResults.getPilotResults();
        Assert.assertTrue(pilotResults.isEmpty());
    }

    @Test
    public void processRaceFromFileMultiLineFile() throws IOException {
        final Race race = new RaceProcessor()
            .processRaceFromFile("src/test/resources/input.log");
        final List<RaceLap> raceLaps = race.getRace();
        final RaceLap lap = raceLaps.get(0);
        Assert.assertEquals(raceLaps.size(), 23);
        Assert.assertEquals(lap.getTime().toString(), "23:49:08.277");
        Assert.assertEquals(lap.getPilot().getComposedCode(), "038 – F.MASSA");
        Assert.assertEquals(lap.getLap(), new Integer(1));
        Assert.assertEquals(lap.getLapTime().toString(), "00:01:02.852");
        Assert.assertEquals(lap.getAvgLapTime().toString(), "44.275");

        final RaceResults raceResults = race.getRaceResults();
        final List<PilotResult> pilotResults = raceResults.getPilotResults();
        final PilotResult pilotResult = pilotResults.get(0);
        Assert.assertEquals(pilotResults.size(), 6);
        Assert.assertEquals(pilotResult.getCompletedLaps(), new Integer(4));
        Assert.assertEquals(pilotResult.getPilot().getComposedCode(), "038 – F.MASS");
        Assert.assertEquals(pilotResult.getPosition(), new Integer(1));
        Assert.assertEquals(pilotResult.getTotalRaceTime().toString(), "00:04:11.578");

        final PilotResult pilotResultLast = pilotResults.get(5);
        Assert.assertEquals(pilotResultLast.getCompletedLaps(), new Integer(3));
        Assert.assertEquals(pilotResultLast.getPilot().getComposedCode(), "011 – S.VETTEL");
        Assert.assertEquals(pilotResultLast.getPosition(), new Integer(6));
        Assert.assertEquals(pilotResultLast.getTotalRaceTime().toString(), "00:06:27.276");

    }
}
