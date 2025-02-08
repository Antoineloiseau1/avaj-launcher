package fr.avaj.simulator;

class WeatherProvider {

    private int simulationSteps = 0;
    static WeatherProvider weatherProvider; // The only instance of the Singleton
    private final String[] weather = {
        "RAIN",
        "FOG",
        "SUN",
        "SNOW"
    };

    private WeatherProvider() { // private default constructor that defines a singleton
    }

    public static WeatherProvider getInstance() {
        if (weatherProvider == null) {
            synchronized (WeatherProvider.class) { //double checked locking Singleton
                if (weatherProvider == null) {
                    weatherProvider = new WeatherProvider();
                }
            }
        }
        return weatherProvider;
    }

    public void updateSimulationSteps() {
        simulationSteps++;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int rand = (p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight() + simulationSteps) % 4;
        return weather[rand];
    }
}
