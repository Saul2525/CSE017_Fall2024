/*
    Saul Toribio
    10/23/24
    CSE017 Fall 2024: ALA 7
    IDE: VSCode; JDK: 11
*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Generic class to impelment an array based list.
*/
public class ArrayList<E> {
    private E[] elements;
    private int size;

    /**
     * Default constructor creates the array with a default length of 10 and sets size to 0.
     * Time complexity: O(1).
    */
    public ArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    /**
     * Constructor with one parameter creates the array with length equal to capacity and sets size to 0.
     * Time complexity: O(1).
     * @param capacity length of the array elements
    */
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Method to add a new item at the end of the list.
     * Time complexity: O(1) or O(n) if the array capacity needs to grow.
     * @param item the value of the item to be added.
     * @return true if item was added successfully, false otherwise
    */
    public boolean add(E item) {
        return add(size, item);
    }

    /**
     * Method to add a new item a given position index.
     * Time complexity: O(n).
     * @param index the position where item should be added.
     * @param item the value of the element to be added.
     * @return true if item was added successfully, false otherwise.
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size.
    */
    public boolean add(int index, E item) {
        if ((index > size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ensureCapacity();
        for (int i = (size - 1); i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = item;
        size++;
        return true;
    }

    /**
     * Get the value of the element at index.
     * Time complexity: O(1).
     * @param index of the element being accessed.
     * @return the value of the element at index.
     * @throws ArrayIndexOutofBounds if index < 0 or index >= size.
    */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Set the value of the element at index.
     * Time complexity: O(1).
     * @param index of the element being modified.
     * @param newValue new value of the element at index.
     * @return the old value of the element at index
     * @throws ArrayIndexOutofBounds if index < 0 or index >= size.
    */
    public E set(int index, E newValue) {
        checkIndex(index);
        E oldValue = elements[index];
        elements[index] = newValue;
        return oldValue;
    }

    /**
     * Get the size of the list.
     * Time complexity: O(1).
     * @return the number of elements in the list.
    */
    public int size() {
        return size;
    }

    /**
     * Clear the list by setting size to 0.
     * Time complexity: O(1).
    */
    public void clear() {
        size = 0;
    }

    /**
     * Predicate to check if the list is empty.
     * Time complexity: O(1).
     * @return true if the list is empty, false otherwise.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Remove the element at a given index.
     * Time complexity: O(n).
     * @param index the position of the element to be removed.
     * @return true if the elements was removed successfully, false otherwise.
     * @throws ArrayIndexOutofBoundsException if index < 0 or index >= size
    */
    public boolean remove(int index) {
        checkIndex(index);
        for (int i = index; i < (size - 1); i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return true;
    }

    /**
     * Resize the length of the array 'elements' to the size of the list.
     * Time complexity: O(n) if trimming needed.
    */
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
     * Grow the length of the array 'elements' by 1.5 if it is full.
     * Time complexity: O(n) if the size reaches the capacity.
    */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCap = (int) (elements.length * 1.5);
            E[] newElements = (E[]) new Object[newCap];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }

            elements = newElements;
        }
    }

    /**
     * Check if the index is valid.
     * Time complexity: O(1).
     * @param index to be checked.
     * @throws ArrayIndexOutOFBoundsException is index is out of bounds.
    */
    private void checkIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1));
        }
    }

    /**
     * Time complexity: O(n).
     * @override toString() from class Object
     * @return a formatted string containing the elements of the list
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
     * Time complexity: O(1).
     * @override iterator() from the interface Collection
     * @return iterator object pointing to the first element the list.
    */
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    /**
     * Inner class to implement the interface Iterator<E>.
    */
    private class ArrayIterator implements Iterator<E> {
        private int current = 0;

        /**
         * Time complexity: O(1).
         * @return true if current did not reach the end of the list, false otherwise.
        */
        public boolean hasNext() {
            return current < size;
        }

        /**
         * Time complexity: O(1).
         * @return the value of the current element and moves the index current to the next element.
         * @throws ArrayIndexOutOfBoundsException if current is out of bounds.
        */
        public E next() {
            if ((current < 0) || (current >= size)) {
                throw new ArrayIndexOutOfBoundsException("No more elements");
            }
            return elements[current++];
        }
    }

    /**
     * Default configuration of listIterator.
     * Time complexity: O(1).
     * @return the ArrayListIterator.
    */
    public ListIterator<E> listIterator() {
        return new ArrayListIterator();
    }

    /**
     * Configuration of listIterator with a inputted index.
     * Time complexity: O(1).
     * @param index to be checked.
     * @return the ArrayListIterator.
     * @throws ArrayIndexOutofBounds if index < 0 or index >= size.
    */
    public ListIterator<E> listIterator(int index) {
        if (index == size) {
            index--;
        }
        checkIndex(index);
        return new ArrayListIterator(index);
    }

    /**
     * A class that implements an array list iterator.
    */
    private class ArrayListIterator implements ListIterator<E> {
        private int current;

        /**
         * Default configuration of ArrayListIterator.
         * Time complexity: O(1).
        */
        public ArrayListIterator() {
            current = 0;
        }

        /**
         * Configuration of ArrayListIterator with a inputted index.
         * Time complexity: O(1).
         * @param index to be checked.
        */
        public ArrayListIterator(int index) {
            current = index;
        }

        /**
         * Throws a UnsupportedOperationException.
        */
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * True if current size of the iterator is less than the size of the array.
         * Time complexity: O(1).
        */
        public boolean hasNext() {
            return (current < size);
        }

        /**
         * True if current is greater than or equal to 0.
         * Time complexity: O(1).
        */
        public boolean hasPrevious() {
            return (current >= 0);
        }

        /**
         * Gets the next item in the array list.
         * Time complexity: O(1).
         * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size.
        */
        public E next() {
            if (current == -1) {
                current = 0;
            }

            if ((current < 0) || (current >= size)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return elements[current++];
        }

        /**
         * Gets the previous item in the array list.
         * Time complexity: O(1).
         * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size.
        */
        public E previous() {
            if (current == size) {
                current--;
            }

            if ((current < 0) || (current >= size)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return elements[current--];
        }

        /**
         * Throws a UnsupportedOperationException.
        */
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * Throws a UnsupportedOperationException.
        */
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * Throws a UnsupportedOperationException.
        */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Throws a UnsupportedOperationException.
        */
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Shuffles the array randomly.
     * Time complexity: O(n).
    */
    public void shuffle() {
        for (int i = 0; i < size; i++) {
            int randomIndex = (int)(Math.random() * size);
            E temp = elements[i];
            elements[i] = elements[randomIndex];
            elements[randomIndex] = temp;
        }
    }

    /**
     * Sorts the array randomly.
     * Time complexity: O(n^2).
    */
    public void sort() {
        for (int i = 0; i < size; i++) {
            int minIndex = i;
            for (int j = i; j < size; j++) {
                if (((Comparable) elements[j]).compareTo(elements[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }

    /**
     * Sorts the array randomly.
     * Time complexity: O(n^2).
     * @param comparator compares two elements.
    */
    public void sort(Comparator<E> comparator) {
        for (int i = 0; i < size; i++) {
            int minIndex = i;
            for (int j = i; j < size; j++) {
                if ((comparator.compare(elements[j], elements[minIndex]) < 0)) {
                    minIndex = j;
                }
            }

            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }
}