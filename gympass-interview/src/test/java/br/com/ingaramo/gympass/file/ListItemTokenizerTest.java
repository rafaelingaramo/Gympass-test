package br.com.ingaramo.gympass.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListItemTokenizerTest {
    @Test
    public void testHeaderOnlyTokenize() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/header-only.log");

        final List<List<String>> tokenize = new ListItemTokenizer().tokenize(lines);
        Assert.assertTrue(tokenize.isEmpty());
    }

    @Test
    public void testSingleLineTokenize() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/single.log");

        final List<List<String>> tokenize = new ListItemTokenizer().tokenize(lines);
        final List<String> firstLine = tokenize.get(0);

        Assert.assertEquals(1, tokenize.size());
        Assert.assertEquals("23:49:08.277", firstLine.get(0));
        Assert.assertEquals("038 – F.MASSA", firstLine.get(1));
        Assert.assertEquals("1", firstLine.get(2));
        Assert.assertEquals("1:02.852", firstLine.get(3));
        Assert.assertEquals("44,275", firstLine.get(4));
    }

    @Test
    public void testMultipleLineTokenize() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/multiple.log");

        final List<List<String>> tokenize = new ListItemTokenizer().tokenize(lines);
        final List<String> firstLine = tokenize.get(0);
        final List<String> lastLine = tokenize.get(7);

        Assert.assertEquals(8, tokenize.size());
        Assert.assertEquals("23:49:08.277", firstLine.get(0));
        Assert.assertEquals("038 – F.MASSA", firstLine.get(1));
        Assert.assertEquals("1", firstLine.get(2));
        Assert.assertEquals("1:02.852", firstLine.get(3));
        Assert.assertEquals("44,275", firstLine.get(4));

        Assert.assertEquals("23:50:15.057", lastLine.get(0));
        Assert.assertEquals("002 – K.RAIKKONEN", lastLine.get(1));
        Assert.assertEquals("2", lastLine.get(2));
        Assert.assertEquals("1:03.982", lastLine.get(3));
        Assert.assertEquals("43,493", lastLine.get(4));
    }
}
