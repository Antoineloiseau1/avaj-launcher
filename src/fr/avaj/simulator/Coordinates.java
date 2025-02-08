package fr.avaj.simulator;

import fr.avaj.exceptions.InvalidCoordinatesException;

public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) throws InvalidCoordinatesException {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new InvalidCoordinatesException("Coordinates cannot be negative");
        }
        if (height > 100) {
            throw new InvalidCoordinatesException("Height must be less than 100");
        }
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getHeight() {
        return height;
    }
}
