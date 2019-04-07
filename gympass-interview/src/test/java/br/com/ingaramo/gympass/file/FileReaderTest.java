package br.com.ingaramo.gympass.file;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FileReaderTest {
    @Test(expected = NullPointerException.class)
    public void testFileReadLiensForNullFile() throws IOException {
        final FileReader reader = new FileReader();
        reader.readFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileReadLinesForEmptyFile() throws IOException {
        final FileReader reader = new FileReader();
        reader.readFile("src/test/resources/empty.log");
    }

    @Test
    public void testFileReadLinesForHeaderOnlyFile() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/header-only.log");

        Assert.assertTrue(lines.isEmpty());
    }

    @Test
    public void testFileReadLinesForSingleLine() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/single.log");

        Assert.assertEquals(lines.size(), 1);
    }

    @Test
    public void testFileReadLinesForMultipleLines() throws IOException {
        final FileReader reader = new FileReader();
        final List<String> lines = reader.readFile("src/test/resources/multiple.log");

        Assert.assertEquals(lines.size(), 8);
    }
}
