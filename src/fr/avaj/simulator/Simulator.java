package fr.avaj.simulator;

public class Simulator {

    public static void main(String[] args) {
        AircraftFactory factory = AircraftFactory.INSTANCE;
        WeatherTower tower = new WeatherTower();
        Flyable h = factory.newAircraft("Baloon", "B2", new Coordinates(2, 2, 4));
        Flyable b = factory.newAircraft("Baloon", "B1", new Coordinates(1, 2, 4));
        h.registerTower(tower);
        b.registerTower(tower);
        b.updateConditions();
        h.updateConditions();

        //     if (args.length != 1) {
        //         System.out.println("Usage: [file.txt]");
        //     } else {
        //         int simulations;
        //         File scenario = new File(args[0]);
        //         try (Scanner scanner = new Scanner(scenario)) {
        //             ArrayList<String[]> tokens = new ArrayList<>();
        //             while (scanner.hasNextLine()) {
        //                 String currentLine = scanner.nextLine();
        //                 tokens.add(currentLine.split(" "));
        //             }
        //             if (isInteger(tokens.get(0)[0])) {  //first line 
        //                 simulations = Integer.parseInt(tokens.get(0)[0]);
        //             } else {
        //                 System.out.println("Error: Invalid input file.");
        //                 return;
        //             }
        //             for (String[] line : tokens) {
        //                 for (int i = 0; line.length > i; i++) {
        //                     System.out.println(line[i]);
        //                 }
        //             }
        //         } catch (FileNotFoundException e) {
        //             System.out.println("Error: File not found - " + scenario.getAbsolutePath());
        //         }
        //     }
        // }
        // public static boolean isInteger(String str) {
        //     try {
        //         Integer.parseInt(str);
        //         return true;
        //     } catch (NumberFormatException e) {
        //         return false;
        //     }
        // }
    }
}
