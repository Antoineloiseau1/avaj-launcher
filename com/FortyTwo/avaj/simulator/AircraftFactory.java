package com.FortyTwo.avaj.simulator;

import com.FortyTwo.avaj.exceptions.InvalidAircraftTypeException;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
  this Singleton class also has a factory design pattern.
  Here I decided to use the build-in enum special Java class and its
  INSTANCE attribute that does the same as:

  AircraftFactory factory;

  private AircraftFactory() {}

  public AircraftFactory getInstance() {
    if(factory == null)
      factory = new AircraftFactory();
    return factoty;
  }

* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
enum AircraftFactory {

    INSTANCE;
    private static int id = 1;

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws InvalidAircraftTypeException {
        return switch (p_type) {
            case "Helicopter" ->
                new Helicopter(id++, p_name, p_coordinates);
            case "JetPlane" ->
                new JetPlane(id++, p_name, p_coordinates);
            case "Baloon" ->
                new Baloon(id++, p_name, p_coordinates);
            default ->
                throw new InvalidAircraftTypeException("AircraftFactory: type \"" + p_type + "\" not supported.\nExpected: \"Baloon\" | \"JetPlane\" | \"Helicopter\"");
        };
    }
}
