/*
    Saul Toribio
    9/5/24
    CSE017 Fall 2024: ALA 2
    IDE: VSCode; JDK: 11
*/

public class InvalidSeatException extends Exception {
    public InvalidSeatException() {
        super("Invalid seat number.");
    }

    public InvalidSeatException(String message) {
        super(message);
    }
}