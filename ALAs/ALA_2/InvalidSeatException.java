/*
    Saul Toribio
    9/5/24
    CSE017 Fall 2024: ALA 2
    IDE: VSCode; JDK: 11
*/

/**
 * A class that extends the class Exception. Deals with exception errors.
*/
public class InvalidSeatException extends Exception {
    /**
     * A method that sends a message. Default configuration.
    */
    public InvalidSeatException() {
        super("Invalid seat number.");
    }

    /**
     * A method that sends a certain message.
    */
    public InvalidSeatException(String message) {
        super(message);
    }
}