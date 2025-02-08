package fr.avaj.simulator;

public class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        System.out.print(this.getClass().getSimpleName() + "#" + name + "(" + id + "): ");
        handleWeather(weather);
    }

    protected void handleWeather(String weather) {
    }

    protected void updateCoordinates(int deltaLongitude, int deltaLatitude, int deltaHeight) {
        int newLongitude = coordinates.getLongitude() + deltaLongitude;
        int newLatitude = coordinates.getLatitude() + deltaLatitude;
        int newHeight = Math.min(Math.max(coordinates.getHeight() + deltaHeight, 0), 100); // Ensure height stays in range
        if (newHeight <= 0) {
            System.out.println(this.getClass().getSimpleName() + "#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
        }

        coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
    }

}
