package fr.avaj.simulator;

import fr.avaj.exceptions.SimulationsNumberException;
import java.io.*;
import java.util.*;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: [file.txt]");
        } else {
            List<String[]> tokens = tokenizeFile(args[0]);
            int simulations;
            try {
                simulations = parseSimulations(tokens.get(0)); //first line of the file 
            } catch (SimulationsNumberException sne) {
                System.out.println(sne.getMessage());
                return;
            }
            System.out.println(simulations);
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
