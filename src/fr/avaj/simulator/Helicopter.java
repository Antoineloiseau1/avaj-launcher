package fr.avaj.simulator;

class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        System.out.print(this.getClass().getSimpleName() + "#" + name + "(" + id + "): ");
        handleWeather(weather);
    }

    private void handleWeather(String weather) {
        switch (weather) {
            case "SUN":
                System.out.println("It is so hot I think I might roast marshmallows on the dashboard!");
                updateCoordinates(10, 0, 2);
                break;
            case "RAIN":
                System.out.println("If we don't make it through this storm, at least we will be well hydrated!");
                updateCoordinates(5, 0, 0);
                break;
            case "FOG":
                System.out.println("I can barely see my own blades, but I do know we're at the Prime Meridian... somewhere.");
                updateCoordinates(1, 0, 0);
                break;
            case "SNOW":
                System.out.println("I think I just saw a snowflake fly through the window... inside the helicopter!");
                updateCoordinates(0, 0, -12);
                break;
        }
    }
}
