package br.com.ingaramo.gympass.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    public List<String> readFile(final String filePath) throws IOException {
        Path input = Paths.get(filePath);

        Stream<String> lines = null;
        try {
            lines = Files.lines(input);
            final List<String> collect = lines.collect(Collectors.toList());
            return collect.subList(1, collect.size());
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
    }
}
