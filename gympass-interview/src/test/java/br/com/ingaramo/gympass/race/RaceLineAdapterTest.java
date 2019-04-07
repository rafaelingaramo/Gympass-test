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
            Assert.assertEquals("Wrong line provided to adapter", e.getMessage());
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

        Assert.assertEquals("23:49:08.277", raceLap.getTime().toString());
        Assert.assertEquals("038 – F.MASSA", raceLap.getPilot().getComposedCode());
        Assert.assertEquals(new Integer(1), raceLap.getLap());
        Assert.assertEquals("00:01:02.852", raceLap.getLapTime().toString());
        Assert.assertEquals("44.275", raceLap.getAvgLapTime().toString());
    }
}
