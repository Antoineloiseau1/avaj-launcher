package fr.avaj.simulator;

import fr.avaj.exceptions.InvalidAircraftTypeException;

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
    private static int count = 1;

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws InvalidAircraftTypeException {
        return switch (p_type) {
            case "Helicopter" ->
                new Helicopter(count++, p_name, p_coordinates);
            case "JetPlane" ->
                new JetPlane(count++, p_name, p_coordinates);
            case "Baloon" ->
                new Baloon(count++, p_name, p_coordinates);
            default ->
                throw new InvalidAircraftTypeException("Aircraft type not supported: " + p_type);
        };
    }
}
