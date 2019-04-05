package br.com.ingaramo.gympass;

import br.com.ingaramo.gympass.race.Race;
import br.com.ingaramo.gympass.race.RaceProcessor;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        final RaceProcessor processor = new RaceProcessor();
        final Race race = processor.processRaceFromFile("src/main/resources/input.log");
        System.out.println(race);
    }
}
