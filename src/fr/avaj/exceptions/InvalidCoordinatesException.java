package fr.avaj.exceptions;

public class InvalidCoordinatesException extends NumberFormatException {

    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
