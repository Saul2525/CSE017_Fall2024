/*
    Saul Toribio
    10/25/24
    CSE017 Fall 2024: PA 6
    IDE: VSCode; JDK: 11
*/

/**
 * A generic class that contains three generic parameters.
*/
public class Triple<E1, E2, E3> {
    private E1 first;
    private E2 second;
    private E3 third;

    /**
     * Creates a triple. Default configuration.
     * @param first A generic parameter.
     * @param second A second generic parameter.
     * @param third A third generic parameter.
    */
    public Triple(E1 first, E2 second, E3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
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
     * Gets a generic parameter.
     * @return The generic parameter.
    */
    public E3 getThird() {
        return third;
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

    /**
     * Sets a generic parameter.
     * @param third A generic parameter.
    */
    public void setThird(E3 third) {
        this.third = third;
    }
}