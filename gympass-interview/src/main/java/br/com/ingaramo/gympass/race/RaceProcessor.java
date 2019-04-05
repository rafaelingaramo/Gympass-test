package br.com.ingaramo.gympass.race;

import br.com.ingaramo.gympass.file.FileReader;
import br.com.ingaramo.gympass.file.ListItemTokenizer;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RaceProcessor {
    private FileReader fileReader;
    private ListItemTokenizer listItemTokenizer;
    private RaceLineAdapter raceLineAdapter;

    public RaceProcessor() {
        this.fileReader = new FileReader();
        this.listItemTokenizer = new ListItemTokenizer();
        this.raceLineAdapter = new RaceLineAdapter();
    }

    public Race processRaceFromFile(final String filePath) throws IOException {
        final List<String> lines = fileReader.readFile(filePath);
        if (lines == null) {
            return new Race();
        }

        final List<List<String>> tokenizeLines = listItemTokenizer.tokenize(lines);
        final Set<RaceLap> raceList = tokenizeLines.stream()
            .map(line -> raceLineAdapter.adaptRaceLapFromLine(line))
            .collect(Collectors.toSet());

        return new Race(raceList);
    }
}
