package fr.avaj.exceptions;

public class InvalidAircraftTypeException extends IllegalArgumentException {

    public InvalidAircraftTypeException(String message) {
        super(message);
    }
}
