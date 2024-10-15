/*
    Saul Toribio
    10/14/24
    CSE017 Fall 2024: PA 5
    IDE: VSCode; JDK: 11
*/

import java.util.Comparator;

/**
 * A class that compares two doubles.
*/
public class ComparatorByDouble implements Comparator<Number> {
    /**
     * Does exactly what it says.
    */
    public int compare(Number obj, Number obj2) {
        return Double.compare(obj.doubleValue(), obj2.doubleValue());
    }
}