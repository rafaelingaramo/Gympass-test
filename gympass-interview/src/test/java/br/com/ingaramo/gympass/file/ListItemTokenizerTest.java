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

        Assert.assertEquals(tokenize.size(), 1);
        Assert.assertEquals(firstLine.get(0), "23:49:08.277");
        Assert.assertEquals(firstLine.get(1), "038 – F.MASSA");
        Assert.assertEquals(firstLine.get(2), "1");
        Assert.assertEquals(firstLine.get(3), "1:02.852");
        Assert.assertEquals(firstLine.get(4), "44,275");
    }

    @Test
    public void testMultipleLineTokenize() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/multiple.log");

        final List<List<String>> tokenize = new ListItemTokenizer().tokenize(lines);
        final List<String> firstLine = tokenize.get(0);
        final List<String> lastLine = tokenize.get(7);

        Assert.assertEquals(tokenize.size(), 8);
        Assert.assertEquals(firstLine.get(0), "23:49:08.277");
        Assert.assertEquals(firstLine.get(1), "038 – F.MASSA");
        Assert.assertEquals(firstLine.get(2), "1");
        Assert.assertEquals(firstLine.get(3), "1:02.852");
        Assert.assertEquals(firstLine.get(4), "44,275");

        Assert.assertEquals(lastLine.get(0), "23:50:15.057");
        Assert.assertEquals(lastLine.get(1), "002 – K.RAIKKONEN");
        Assert.assertEquals(lastLine.get(2), "2");
        Assert.assertEquals(lastLine.get(3), "1:03.982");
        Assert.assertEquals(lastLine.get(4), "43,493");
    }
}
