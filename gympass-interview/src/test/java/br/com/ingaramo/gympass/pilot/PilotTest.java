package br.com.ingaramo.gympass.pilot;

import org.junit.Assert;
import org.junit.Test;

public class PilotTest {
    @Test(expected = NumberFormatException.class)
    public void testPilotCreationWithWrongString() {
        new Pilot("");
    }

    @Test
    public void testPilotCreationWithOkString() {
        final String composed = "033 – K.RAIKONNEN";
        final Pilot pilot = new Pilot(composed);
        Assert.assertEquals("K.RAIKONNEN", pilot.getName());
        Assert.assertEquals(new Integer(33), pilot.getCode());
        Assert.assertEquals(composed, pilot.getComposedCode());
    }
}
