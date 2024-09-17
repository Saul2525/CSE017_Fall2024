/*
    Saul Toribio
    9/16/24
    CSE017 Fall 2024: PA 2
    IDE: VSCode; JDK: 11
*/

/**
 * A extended exception class that handles invalid media data.
*/
public class InvalidMediaAttribute extends Exception {
    /**
     * A method that sends a message. Default configuration.
    */
    public InvalidMediaAttribute() {
        super("Invalid media attribute.");
    }

    /**
     * A method that sends a certain message.
     * @param message Message of choice.
    */
    public InvalidMediaAttribute(String message) {
        super(message);
    }
}