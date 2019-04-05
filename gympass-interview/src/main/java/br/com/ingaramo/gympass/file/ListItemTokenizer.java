package br.com.ingaramo.gympass.file;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ListItemTokenizer {
    private static final String REGEXP;
    private static final Pattern PATTERN;

    static {
        REGEXP = "(\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{3})\\s+(\\d{1,3}\\s\\–\\s\\w+\\.\\w+)\\s+(\\d)"
            + "\\s+(\\d\\:\\d{2}\\.\\d{3})\\s+(\\d+\\,\\d+)";
        PATTERN = Pattern.compile(REGEXP);
    }

    public List<List<String>> tokenize(final List<String> lines) {
        final List<List<String>> collect = lines.stream()
            .map(line -> tokenizeLine(line)).collect(Collectors.toList());
        return collect;
    }

    private List<String> tokenizeLine(final String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            return Collections.emptyList();
        }

        final String time = matcher.group(1);
        final String pilot = matcher.group(2);
        final String lap = matcher.group(3);
        final String lapTime = matcher.group(4);
        final String lapAvgTime = matcher.group(5);

        return Arrays.asList(new String[]{time, pilot, lap, lapTime, lapAvgTime});
    }

    public static void main(String[] args) throws IOException {
        final List<String> strings = new FileReader().readFile("src/main/resources/input.log");
        final List<List<String>> tokenize = new ListItemTokenizer().tokenize(strings);

        tokenize.forEach(allLines -> System.out.println(allLines));
    }
}
