/*
    Saul Toribio
    11/13/24
    CSE017 Fall 2024: ALA 8
    IDE: VSCode; JDK: 11
*/

import java.util.Iterator;

/**
 * A generic class that contains a genric array to store information.
*/
public class ArrayList<E> {
    private E[] elements;
    private int size;

    public static int containsIterations, removeIterations, addIterations;

    /**
     * Default configuration of ArrayList.
     * Time complexity: O(1).
    */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    /**
     * Creates a ArrayList with a specified capacity.
     * Time complexity: O(1).
     * @param capacity The size of the list.
    */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Default method of add.
     * Time complexity: O(n).
     * @param item A item.
     * @return True if the item was added to the list.
    */
    public boolean add(E item) {
        return add(size, item);
    }

    /**
     * Adds a item to the generic list elements.
     * Time complexity: O(n).
     * @param index The location for the item to be added in the list.
     * @param item A item.
     * @return True if the item was added to the list.
     * @throws ArrayIndexOutOfBoundsException If the index is below 0 or greater then the size of the list.
    */
    public boolean add(int index, E item) {
        if ((index > size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ensureCapacity();

        addIterations = 0;
        for (int i = (size - 1); i >= index; i--) {
            addIterations++;
            elements[i + 1] = elements[i];
        }
        elements[index] = item;
        size++;
        return true;
    }

    /**
     * Gets an item from elements at the specified index.
     * @param index An int for a item at a specified location.
     * @return The item at the specified index.
    */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Sets a item from elements at the specified index.
     * @param indexnAn int for a item at a specified location.
     * @param newValue Replacement value.
     * @return The old value at the specified location.
    */
    public E set(int index, E newValue) {
        checkIndex(index);
        E oldValue = elements[index];
        elements[index] = newValue;
        return oldValue;
    }

    /**
     * @return The size of elements.
    */
    public int size() {
        return size;
    }

    /**
     * Sets size to 0.
    */
    public void clear() {
        size = 0;
    }

    /**
     * @return True if size is equal to 0, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Removes a item at the specified index.
     * @param index The specified location.
     * @return True if an item was removed.
    */
    public boolean remove(int index) {
        checkIndex(index);
        for (int i = index; i < (size - 1); i++) {
            removeIterations++;
            elements[i] = elements[i + 1];
        }
        size--;
        return true;
    }

    /**
     * Clones elements?
    */
    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (size != elements.length) {
            E[] newElements = (E[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    /**
     * Ensures that elements has the capacity to add new items.
    */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCap = (int) (elements.length * 1.5);
            E[] newElements = (E[]) new Object[newCap];
            for (int i = 0; i < size; i++) {
                addIterations++;
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    /**
     * Checks if index is less than 0 or greater than size.
     * @param index The index inputted.
     * @throws ArrayIndexOutOfBoundsException If the test is failed.
    */
    private void checkIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1) + ".");
        }
    }

    /**
     * Prints the content of elements.
    */
    public String toString() {
        String output = "[";
        for (int i = 0; i < (size - 1); i++) {
            output += elements[i] + " ";
        }
        output += elements[size - 1] + "]";
        return output;
    }

    /**
     * @return A ArrayIterator.
    */
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    /**
     * A class that implements Iterator to move throughout elements.
    */
    private class ArrayIterator implements Iterator<E> {
        private int current = 0;

        /**
         * Checks if current is less than size.
        */
        public boolean hasNext() {
            return (current < size);
        }

        /**
         * Returns the next element in elements.
        */
        public E next() {
            if ((current < 0) || (current >= size)) {
                throw new ArrayIndexOutOfBoundsException("No more elements.");
            }
            return elements[current++];
        }
    }

    /**
     * Checks to see if a object is currently within elements.
     * Time complexity: O(n).
     * @param o A object.
     * @return True if the object is within elements, otherwise false.
    */
    public boolean contains(Object o) {
        containsIterations = 0;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            containsIterations++;
            E tempValue = iterator.next();
            if (tempValue.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a object from the list elements.
     * Time complexity: O(n).
     * @param o A object.
     * @return True if the object was removed from elements, otherwise false.
    */
    public boolean remove(Object o) {
        removeIterations = 0;
        for (int i = 0; i < size; i++) {
            removeIterations++;
            if (elements[i].equals(o)) {
                return remove(i);
            }
        }
        return false;
    }
}