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
        final List<RaceLap> raceLaps = race.getRaceLaps();
        Assert.assertTrue(raceLaps.isEmpty());

        try {
            race.getRaceResults();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Race not provided", e.getMessage());
            throw e;
        }
    }

    @Test
    public void processRaceFromFileSingleLineFile() throws IOException {
        final Race race = new RaceProcessor()
            .processRaceFromFile("src/test/resources/single.log");
        final List<RaceLap> raceLaps = race.getRaceLaps();
        final RaceLap lap = raceLaps.get(0);
        Assert.assertEquals(1, raceLaps.size());
        Assert.assertEquals("23:49:08.277", lap.getTime().toString());
        Assert.assertEquals("038 – F.MASSA", lap.getPilot().getComposedCode());
        Assert.assertEquals(new Integer(1), lap.getLap());
        Assert.assertEquals("00:01:02.852", lap.getLapTime().toString());
        Assert.assertEquals("44.275", lap.getAvgLapTime().toString());

        final RaceResults raceResults = race.getRaceResults();
        final List<PilotResult> pilotResults = raceResults.getPilotResults();
        Assert.assertTrue(pilotResults.isEmpty());
    }

    @Test
    public void processRaceFromFileMultiLineFile() throws IOException {
        final Race race = new RaceProcessor()
            .processRaceFromFile("src/test/resources/input.log");
        final List<RaceLap> raceLaps = race.getRaceLaps();
        final RaceLap lap = raceLaps.get(0);
        Assert.assertEquals(23, raceLaps.size());
        Assert.assertEquals("23:49:08.277", lap.getTime().toString());
        Assert.assertEquals("038 – F.MASSA", lap.getPilot().getComposedCode());
        Assert.assertEquals(new Integer(1), lap.getLap());
        Assert.assertEquals("00:01:02.852", lap.getLapTime().toString());
        Assert.assertEquals("44.275", lap.getAvgLapTime().toString());

        final RaceResults raceResults = race.getRaceResults();
        final List<PilotResult> pilotResults = raceResults.getPilotResults();
        final PilotResult pilotResult = pilotResults.get(0);
        Assert.assertEquals(6, pilotResults.size());
        Assert.assertEquals(new Integer(4), pilotResult.getCompletedLaps());
        Assert.assertEquals("038 – F.MASS", pilotResult.getPilot().getComposedCode());
        Assert.assertEquals(new Integer(1), pilotResult.getPosition());
        Assert.assertEquals("00:04:11.578", pilotResult.getTotalRaceTime().toString());

        final PilotResult pilotResultLast = pilotResults.get(5);
        Assert.assertEquals(new Integer(3), pilotResultLast.getCompletedLaps());
        Assert.assertEquals("011 – S.VETTEL", pilotResultLast.getPilot().getComposedCode());
        Assert.assertEquals(new Integer(6), pilotResultLast.getPosition());
        Assert.assertEquals("00:06:27.276", pilotResultLast.getTotalRaceTime().toString());
    }
}
