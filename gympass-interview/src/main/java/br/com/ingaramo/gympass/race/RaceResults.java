package br.com.ingaramo.gympass.race;

import br.com.ingaramo.gympass.pilot.PilotResult;

import java.util.List;

public class RaceResults {
    private List<PilotResult> pilotResults;

    public List<PilotResult> getPilotResults() {
        return pilotResults;
    }

    public void setPilotResults(List<PilotResult> pilotResults) {
        this.pilotResults = pilotResults;
    }

    RaceResults(List<PilotResult> pilotResults) {
        this.pilotResults = pilotResults;
    }

    @Override
    public String toString() {
        return "RaceResults{" +
            "pilotResults=" + pilotResults +
            '}';
    }

    public void printOutput() {
        System.out.println("Resultados da Corrida: ");
        pilotResults.forEach(pilotResult -> {
            System.out.println(String.format("%s terminou em: %d com um total de %d voltas em %s",
                pilotResult.getPilot().getComposedCode(),
                pilotResult.getPosition(),
                pilotResult.getCompletedLaps(),
                pilotResult.getTotalRaceTime()));
        });
    }
}
