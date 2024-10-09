/*
    Saul Toribio
    10/3/24
    CSE017 Fall 2024: ALA 5
    IDE: VSCode; JDK: 11
*/

/**
 * A generic class.
*/
public class Pair<E1, E2> {
    private E1 first;
    private E2 second;

    /**
     * Creates a pair. Default configuration.
     * @param f A generic object.
     * @param s Another generic object.
    */
    public Pair(E1 f, E2 s) {
        first = f;
        second = s;
    }

    /**
     * Returns the generic object.
     * @return The generic object.
    */
    public E1 getFirst() {
        return first;
    }

    /**
     * Returns the generic object.
     * @return The generic object.
    */
    public E2 getSecond() {
        return second;
    }

    /**
     * Sets the generic object.
     * @param f The generic object.
    */
    public void setFirst(E1 f) {
        first = f;
    }

    /**
     * Sets the generic object.
     * @param s The generic object.
    */
    public void setSecond(E2 s) {
        second = s;
    }

    /**
     * Prints the the generic objects.
    */
    public String toString() {
        return String.format("(%s, %s)", first.toString(), second.toString());
    }

    /**
     * Compares two generic objects.
    */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            @SuppressWarnings("rawtypes")
            Pair<E1, E2> p = (Pair) o;
            return (this.first.equals(p.first) && this.second.equals(p.second));
        }

        return false;
    }
}