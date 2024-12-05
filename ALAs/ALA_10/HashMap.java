/*
    Saul Toribio
    12/2/24
    CSE017 Fall 2024: ALA 10
    IDE: VSCode; JDK: 11
*/

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that implements a hashtable.
*/
public class HashMap<K, V> {
    private LinkedList<MapEntry<K, V>>[] hashTable;
    private double loadFactor;
    private int size;

    public static int getIterations, putIterations, removeIterations;

    /**
     * Default configuration of a HashMap.
    */
    public HashMap() {
        this(100, .9);
    }

    /**
     * A HashMap with a defined capacity.
     * @param capacity The size for the HashMap.
    */
    public HashMap(int capacity) {
        this(capacity, .9);
    }

    /**
     * A HashMap with a defined capacity and load factor.
     * @param capacity The size for the HashMap.
     * @param loadFactor The load factor.
    */
    @SuppressWarnings("unchecked")
    public HashMap(int capacity, double loadFactor) {
        hashTable = new LinkedList[trimToPowerOf2(capacity)];
        this.loadFactor = loadFactor;
        size = 0;
    }

    /**
     * Creates a hashtable with a size that is the closest power of two to capacity.
     * @param capacity The old size of the HashMap.
     * @return The new size of the HashMap.
    */
    private int trimToPowerOf2(int capacity) {
        int tempCap = 1;
        while (tempCap < capacity) {
            tempCap = tempCap << 1;
        }
        return tempCap;
    }

    /**
     * The hash function. Does stuff.
     * @param hashCode The hash code of the key.
     * @return A valid index in the hashtable.
    */
    private int hash(int hashCode) {
        return hashCode & (hashTable.length - 1);
    }

    /**
     * @return The size of the hashtable.
    */
    public int size() {
        return size;
    }

    /**
     * Clears the HashMap.
    */
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                hashTable[i].clear();
            }
        }
    }

    /**
     * @return True if the size of the HashMap is 0, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Search method.
     * @param key To be serached.
     * @return True if key was found, false otherwise.
    */
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    /**
     * Method to get the value of a key.
     * @param key To be serached.
     * @return The value of the key if found, null otherwise.
    */
    public V get(K key) {
        getIterations = 0;
        int HTIndex = hash(key.hashCode());
        if (hashTable[HTIndex] != null) {
            LinkedList<MapEntry<K, V>> linkedList = hashTable[HTIndex];
            for (MapEntry<K, V> entry : linkedList) {
                getIterations++;
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Method to remove a pair from the hashtable.
     * @param key To be searched and removed.
    */
    public void remove(K key) {
        removeIterations = 0;
        int HTIndex = hash(key.hashCode());
        if (hashTable[HTIndex] != null) {
            LinkedList<MapEntry<K, V>> linkedList = hashTable[HTIndex];
            for (MapEntry<K, V> entry : linkedList) {
                removeIterations++;
                if (entry.getKey().equals(key)) {
                    linkedList.remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    /**
     * Method to add a pair (key,value) to the hashtable.
     * @param key To be added.
     * @param value Of the key to be added.
     * @return Old value if the key was found or the new value if key was not found.
    */
    public V put(K key, V value) {
        putIterations = 0;
        V val = get(key);
        putIterations += getIterations;

        if (val != null) {
            int HTIndex = hash(key.hashCode());
            LinkedList<MapEntry<K, V>> linkedList = hashTable[HTIndex];
            for (MapEntry<K, V> entry : linkedList) {
                putIterations++;
                if (entry.getKey().equals(key)) {
                    V old = entry.getValue();
                    entry.setValue(value);
                    return old;
                }
            }
        }

        if (size >= (hashTable.length * loadFactor)) {
            rehash();
        }

        int HTIndex = hash(key.hashCode());

        if (hashTable[HTIndex] == null) {
            hashTable[HTIndex] = new LinkedList<>();
        }

        hashTable[HTIndex].add(new MapEntry<>(key, value));
        size++;
        return value;
    }

    /**
     * Method to rehash the hashtable.
    */
    @SuppressWarnings("unchecked")
    private void rehash() {
        ArrayList<MapEntry<K, V>> list = toList();
        hashTable = new LinkedList[hashTable.length << 1];
        size = 0;

        for (MapEntry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Method to return the pairs (key,value) stored in the hashtable.
     * @return An array list with all the pairs (key,value).
    */
    public ArrayList<MapEntry<K, V>> toList() {
        ArrayList<MapEntry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                LinkedList<MapEntry<K, V>> linkedList = hashTable[i];
                for (MapEntry<K, V> entry : linkedList) {
                    list.add(entry);
                }
            }
        }
        return list;
    }

    /**
     * Prints the contents of the HashMap.
    */
    public String toString() {
        String out = "[";
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                for (MapEntry<K, V> entry : hashTable[i]) {
                    out += entry.toString();
                }
                out += "\n";
            }
        }
        out += "]";
        return out;
    }

    /**
     * Goes through each spot in the HashMap to find the one with the most items.
    */
    public int collisions() {
        int maxSize = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                if (hashTable[i].size() > maxSize) {
                    maxSize = hashTable[i].size();
                }
            }
        }
        return maxSize;
    }
}