package br.com.ingaramo.gympass.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    public List<String> readFile(final String filePath) throws IOException {
        Path input = Paths.get(filePath);
        final List<String> collect = Files.lines(input).collect(Collectors.toList());
        return collect.subList(1, collect.size());
    }
}
