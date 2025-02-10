package fr.avaj.simulator;

import java.util.Random;

class WeatherProvider {

    int rand = 0;
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

    public void randomize() {
        Random random = new Random();
        rand = random.nextInt(100) + 1;

    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int index = ((p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight()) + rand) % 4;
        return weather[index];
    }
}
