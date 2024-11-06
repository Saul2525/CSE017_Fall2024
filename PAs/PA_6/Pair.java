/*
    Saul Toribio
    10/25/24
    CSE017 Fall 2024: PA 6
    IDE: VSCode; JDK: 11
*/

/**
 * A generic class that contains two generic parameters.
*/
public class Pair<E1, E2> {
    private E1 first;
    private E2 second;

    /**
     * Creates a pair. Default configuration.
     * @param first A generic parameter.
     * @param second A second generic parameter.
    */
    public Pair(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets a generic parameter.
     * @return The generic parameter.
    */
    public E1 getFirst() {
        return first;
    }

    /**
     * Gets a generic parameter.
     * @return The generic parameter.
    */
    public E2 getSecond() {
        return second;
    }

    /**
     * Not used.
    */
    public String toString() {
        return String.format("");
    }

    /**
     * Sets a generic parameter.
     * @param first A generic parameter.
    */
    public void setFirst(E1 first) {
        this.first = first;
    }

    /**
     * Sets a generic parameter.
     * @param second A generic parameter.
    */
    public void setSecond(E2 second) {
        this.second = second;
    }
}