package fr.avaj.simulator;

class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "RAIN":
                System.out.println(this.getClass().getSimpleName() + ": It's Raining");
                break;
            case "FOG":
                System.out.println(this.getClass().getSimpleName() + ": It's FOGGY");
                break;
            case "SUN":
                System.out.println(this.getClass().getSimpleName() + ": It's Sunny");
                break;
            case "SNOW":
                System.out.println(this.getClass().getSimpleName() + ": It's Snowy");
                break;
            default:
                throw new AssertionError();
        }
    }

}
