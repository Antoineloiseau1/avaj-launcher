package fr.avaj.simulator;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    /* * * * * * * * * * * * * * * * * * * *
        Part of Observer Design Pattern:
        Notify the WeatherTower that a new flyable 
        has "subscribed" and can now be notified for 
        a conditionChanged(). 
    * * * * * * * * * * * * * * * * * * * * */
    public void registerTower(WeatherTower p_tower) {
        weatherTower = p_tower;
        weatherTower.register(this);
    }
}
