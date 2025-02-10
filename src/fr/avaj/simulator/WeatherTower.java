package fr.avaj.simulator;

public class WeatherTower extends Tower {

    //Access the only instance (Singleton) of WeatherProdiver then calls its getCurrentWeather function.
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
    }

    public void weatherChanged() {
        WeatherProvider.getInstance().randomize();
        conditionChanged();
    }
}
