package fr.avaj.simulator;

class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    protected void handleWeather(String weather) {
        switch (weather) {
            case "SUN":
                System.out.println("To the Moon!");
                updateCoordinates(2, 0, 4);
                break;
            case "RAIN":
                System.out.println("We are getting wet here!");
                updateCoordinates(0, 0, -5);
                break;
            case "FOG":
                System.out.println("At least we are getting up...");
                updateCoordinates(0, 0, -3);
                break;
            case "SNOW":
                System.out.println("Time to make snowmen...");
                updateCoordinates(0, 0, -15);
                break;

        }
    }
}
