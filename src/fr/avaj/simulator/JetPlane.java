package fr.avaj.simulator;

class JetPlane extends Aircraft {

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    protected void handleWeather(String weather) {
        switch (weather) {
            case "SUN":
                System.out.println("It is so sunny, I am thinking of ordering a cold drink!");
                updateCoordinates(0, 10, 2);
                break;
            case "RAIN":
                System.out.println("I guess someone up there decided to water the plants!");
                updateCoordinates(0, 5, 0);
                break;
            case "FOG":
                System.out.println("Can someone remind me what direction east is again?");
                updateCoordinates(0, 1, 0);
                break;
            case "SNOW":
                System.out.println("Well, if this keeps up, we might land in a snow globe!");
                updateCoordinates(0, 0, 7);
                break;
        }
    }
}
