package br.com.ingaramo.gympass.race;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RaceLineAdapterTest {
    @Test(expected = IllegalArgumentException.class)
    public void testAdaptWrongArgument() {
        try {
            new RaceLineAdapter().adaptRaceLapFromLine(
                Arrays.asList(new String[]{""})
            );
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Wrong line provided to adapter");
            throw e;
        }
    }

    @Test
    public void testAdaptOk() {
        final RaceLap raceLap =  new RaceLineAdapter().adaptRaceLapFromLine(
            Arrays.asList(new String[]{"23:49:08.277",
              "038 – F.MASSA",
              "1",
              "1:02.852",
              "44,275"
            })
        );

        Assert.assertEquals(raceLap.getTime().toString(), "23:49:08.277");
        Assert.assertEquals(raceLap.getPilot().getComposedCode(), "038 – F.MASSA");
        Assert.assertEquals(raceLap.getLap(), new Integer(1));
        Assert.assertEquals(raceLap.getLapTime().toString(), "00:01:02.852");
        Assert.assertEquals(raceLap.getAvgLapTime().toString(), "44.275");
    }
}
