package fr.avaj.simulator;

import fr.avaj.exceptions.InvalidCoordinatesException;

class Coordinates {

    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int p_longitude, int p_latitude, int p_height) throws InvalidCoordinatesException { //Package-private constructor
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new InvalidCoordinatesException("Coordinates must be positive");
        }
        if (height > 100) {
            throw new InvalidCoordinatesException("Height must not exceed 100");
        }
        longitude = p_longitude;
        latitude = p_latitude;
        height = p_height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
