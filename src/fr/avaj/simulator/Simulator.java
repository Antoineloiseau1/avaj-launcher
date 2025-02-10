package fr.avaj.simulator;

import fr.avaj.exceptions.InvalidAircraftTypeException;
import fr.avaj.exceptions.InvalidCoordinatesException;
import fr.avaj.exceptions.InvalidScenarioException;
import fr.avaj.exceptions.SimulationsNumberException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: [file.txt]");
        } else {

            List<String[]> tokens = null;
            try {
                tokens = tokenizeFile(args[0]);
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
                return;
            }

            int simulations = 0;
            try {
                if (!tokens.isEmpty()) {
                    simulations = parseSimulations(tokens.get(0)); //first line of the file
                } else {
                    System.out.println("Scenario file is empty.");
                    return;
                }
            } catch (SimulationsNumberException sne) {
                System.out.println(sne.getMessage());
                return;
            }

            WeatherTower tower = new WeatherTower();
            List<Flyable> flyables = null;
            try {
                if (tokens.size() > 1) {
                    flyables = parseFile(tokens.subList(1, tokens.size()));
                } else {
                    System.out.println("Missing Aircraft(s) in scenario file.");
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            try {
                File file = new File("simulation.txt");
                if (!file.exists()) {
                    boolean created = file.createNewFile();
                    if (!created) {
                        System.out.println("Failed to create new file");
                        return;
                    }
                }
                PrintStream pStream = new PrintStream(file);
                System.setOut(pStream); //"simulation.txt is now the output of all System.out.print() calls"
                for (Flyable flyable : flyables) {
                    flyable.registerTower(tower);
                }

                while (simulations-- > 0) {
                    tower.conditionChanged();
                }
                pStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<String[]> tokenizeFile(String fileName) throws FileNotFoundException {
        List<String[]> tokens = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] words = line.split("\\s+"); // Whitespace characters
                if (words.length > 0 && !words[0].equals("")) {
                    tokens.add(words);
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return tokens;
    }

    private static List<Flyable> parseFile(List<String[]> tokens) throws InvalidScenarioException {
        AircraftFactory factory = AircraftFactory.INSTANCE;
        List<Flyable> flyables = new ArrayList<>();
        int i = 2;
        for (String[] line : tokens) {
            if (line.length != 5) {
                throw new InvalidScenarioException("[Line " + i + "]: Invalid format: \"" + String.join(" ", line) + "\"\nexpected: [TYPE] [NAME] [LONGITUDE] [LATITUDE] [HEIGHT]");
            }
            try {
                int longitude = Integer.parseInt(line[2]);
                int latitude = Integer.parseInt(line[3]);
                int height = Integer.parseInt(line[4]);
                flyables.add(factory.newAircraft(line[0], line[1], new Coordinates(longitude, latitude, height)));
            } catch (InvalidCoordinatesException | InvalidAircraftTypeException e) {
                System.out.print("[Line " + i + "]: ");
                throw e;
            } catch (NumberFormatException nfe) {
                System.out.print("[Line " + i + "]: Invalid Coordinates format ");
                throw nfe;
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
