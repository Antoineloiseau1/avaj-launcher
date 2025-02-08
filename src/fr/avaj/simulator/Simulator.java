package fr.avaj.simulator;

import fr.avaj.exceptions.InvalidAircraftTypeException;
import fr.avaj.exceptions.InvalidCoordinatesException;
import fr.avaj.exceptions.InvalidScenarioException;
import fr.avaj.exceptions.SimulationsNumberException;
import java.io.*;
import java.util.*;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: [file.txt]");
        } else {

            List<String[]> tokens = tokenizeFile(args[0]);
            int simulations = 0;

            try {
                simulations = parseSimulations(tokens.get(0)); //first line of the file 
            } catch (SimulationsNumberException sne) {
                System.out.println(sne.getMessage());
                return;
            }

            WeatherTower tower = new WeatherTower();
            List<Flyable> flyables = null;
            try {
                flyables = parseFile(tokens.subList(1, tokens.size()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            for (Flyable flyable : flyables) {
                flyable.registerTower(tower);
            }

            while (simulations-- > 0) {
                tower.conditionChanged();
            }

        }

    }

    private static List<String[]> tokenizeFile(String fileName) {
        List<String[]> tokens = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] words = line.split("\\s+"); // Whitespace characters
                tokens.add(words);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return tokens;
    }

    private static List<Flyable> parseFile(List<String[]> tokens) throws InvalidScenarioException {
        AircraftFactory factory = AircraftFactory.INSTANCE;
        List<Flyable> flyables = new ArrayList<>();
        int i = 2;
        for (String[] line : tokens) {
            if (line.length != 5) {
                throw new InvalidScenarioException("[Line " + i + "]: Invalid format: \"" + String.join(" ", line) + "\"\nexpected: [TYPE] [NAME] [LONGITUDE] [LATITUDE] [HEIGHT]"); //TODO: Custom Exception Here
            }
            try {
                flyables.add(factory.newAircraft(line[0], line[1], new Coordinates(Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]))));
            } catch (InvalidCoordinatesException | InvalidAircraftTypeException e) {
                System.out.print("[Line " + i + "]: ");
                throw e;
            }
            i++;
        }
        return flyables;
    }

    private static int parseSimulations(String[] firstline) throws SimulationsNumberException {
        SimulationsNumberException sne = new SimulationsNumberException(
                "[line 1]: Unexpected format:  \""
                + String.join(" ", firstline)
                + "\"\nExpected positive number"
        );
        if (firstline.length > 1) {
            throw sne;
        }
        int simulations = 0;
        try {
            simulations = Integer.parseInt(firstline[0]);
            if (simulations < 1) {
                throw sne;
            }
        } catch (NumberFormatException nfe) {
            throw sne;
        }
        return simulations;
    }
}
