/*
    Saul Toribio
    11/26/24
    CSE017 Fall 2024: PA 9
    IDE: VSCode; JDK: 11
*/

/**
 * A class that creates a key-value pair.
*/
public class MapEntry<K, V> {
    private K key;
    private V value;

    /**
     * Default configuration of a MapEntry.
    */
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return A generic key.
    */
    public K getKey() {
        return key;
    }

    /**
     * @return A generic value.
    */
    public V getValue() {
        return value;
    }

    /**
     * Sets the key.
     * @param key A generic key. Can be anything.
    */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Sets the value.
     * @param value A generic value. Can be anything.
    */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Prints the key-value pair.
    */
    public String toString() {
        return ("(" + key + ", " + value + ")");
    }
}