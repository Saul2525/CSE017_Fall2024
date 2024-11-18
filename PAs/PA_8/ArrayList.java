/*
    Saul Toribio
    11/18/24
    CSE017 Fall 2024: PA 8
    IDE: VSCode; JDK: 11
*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A generic class that implements Cloneable and List to create a ArrayList.
*/
public class ArrayList<E> implements Cloneable, List<E> {
    private E[] elements;
    private int size;

    /**
     * Default configuration of a ArrayList.
     * Time complexity: O(1).
    */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    /**
     * An ArrayList with a specified capacity.
     * Time complexity: O(1).
    */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Time complexity: O(1).
     * @return The size of the ArrayList.
    */
    public int size() {
        return size;
    }

    /**
     * Sets size to 0.
     * Time complexity: O(1).
    */
    public void clear() {
        size = 0;
    }

    /**
     * Checks to see if the ArrayList is empty.
     * Time complexity: O(1).
     * @return True if the ArrayList is empty, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * The main method of add.
     * Time complexity: O(n).
     * @param element A element. Can be anything.
     * @return True if the element was added to elements, otherwise false.
    */
    public boolean add(E element) {
        return add(size, element);
    }

    /**
     * The helper method of add. Adds a element to elements.
     * Time complexity: O(n).
     * @param element A element. Can be anything.
     * @return True if the element was added to elements, otherwise false.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of elements.
    */
    public boolean add(int index, E element) {
        if ((index > size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ensureCapacity();

        for (int i = (size - 1); i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = element;
        size++;
        return true;
    }

    /**
     * Checks if elements contains the specified object.
     * Time complexity: O(n).
     * @param object A object. Can be anything.
     * @return True if the element is within elements, otherwise false.
    */
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a element from the specified index.
     * Time complexity: O(1).
     * @param index The index to be checked.
     * @return The element at the specified index.
    */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Replaces a old element with a new element at the specified index.
     * Time complexity: O(1).
     * @param index The index for the old element.
     * @param newElement The element replacing the old element.
     * @return The old element.
    */
    public E set(int index, E newElement) {
        checkIndex(index);
        E oldElement = elements[index];
        elements[index] = newElement;
        return oldElement;
    }

    /**
     * Removes a specified object from elements.
     * Time complexity: O(n).
     * @param object A object. Can be anything.
     * @return True if the specified object from elements was found, otherwise false.
    */
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an element from elements at the specified index.
     * Time complexity: O(n).
     * @param index The index of the removed element.
     * @return The removed element.
    */
    public E remove(int index) {
        checkIndex(index);
        E element = elements[index];

        for (int i = index; i < (size - 1); i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return element;
    }

    /**
     * Time complexity: O(1).
     * @return A ArrayListIterator.
    */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Time complexity: O(1).
     * @return A ArrayListIterator.
    */
    public ListIterator<E> listIterator() {
        return new ArrayListIterator();
    }

    /**
     * Time complexity: O(1).
     * @param index The index.
     * @return A ArrayListIterator.
    */
    public ListIterator<E> listIterator(int index) {
        return new ArrayListIterator(index);
    }

    /**
     * Returns a String containing the contents of ArrayList.
     * Time complexity: O(n).
    */
    public String toString() {
        String output = "[";
        for (int i = 0; i < (size - 1); i++) {
            output += elements[i] + " ";
        }

        if (size > 0) {
            output += elements[size - 1];
        }
        output += "]";
        return output;
    }

    /**
     * Reduces the capacity of an array to match its current size.
     * Time complexity: O(n).
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
     * Time complexity: O(1).
     * @param index The index.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than or equal to size.
    */
    private void checkIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1) + ".");
        }
    }

    /**
     * Ensures that the array elements have enough space to add new objects.
     * Time complexity: O(n).
    */
    @SuppressWarnings("unchecked")
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
     * A private class that implements ListIterator to iterator through the ArrayList.
    */
    private class ArrayListIterator implements ListIterator<E> {
        private int current = -1;

        /**
         * Unused constructor.
        */
        public ArrayListIterator() {
        }

        /**
         * A ArrayListIterator with a specific start index.
         * Time complexity: O(1).
         * @param index
        */
        public ArrayListIterator(int index) {
            checkIndex(index);
            current = index;
        }

        /**
         * Used to determine if the iterator can move backward to the previous element.
         * Time complexity: O(1).
        */
        @Override public boolean hasPrevious() {
            return (current > 0);
        }

        /**
         * Used to determine if the iterator can move forward to the next element.
         * Time complexity: O(1).
        */
        @Override public boolean hasNext() {
            return (current < (size - 1));
        }

        /**
         * Gets the previous element.
         * Time complexity: O(1).
         * @throws NoSuchElementException If current is less than or equal to 0.
        */
        @Override public E previous() {
            if ((current <= 0) || (current > size)) {
                throw new NoSuchElementException();
            }
            current--;
            E element = elements[current];

            if (current == 0) {
                current = -1;
            }
            return element;
        }

        /**
         * Gets the next element.
         * Time complexity: O(1).
         * @throws NoSuchElementException If current is greater than or equal to size.
        */
        @Override public E next() {
            if ((current < -1) || (current > (size - 1))) {
                throw new NoSuchElementException();
            }
            current++;
            E val = elements[current];

            if (current == (size - 1)) {
                current = size;
            }
            return val;
        }

        /**
         * Unused method.
        */
        @Override public void add(E element) {
            throw new UnsupportedOperationException();
        }

        /**
         * Unused method.
        */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Unused method.
        */
        @Override public void set(E element) {
            throw new UnsupportedOperationException();
        }

        /**
         * Unused method.
        */
        @Override public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * Unused method.
        */
        @Override public int nextIndex() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Get the first element in elements.
     * Time complexity: O(1).
     * @return The first element.
     * @throws NoSuchElementException If elements is empty.
    */
    @Override public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    /**
     * Get the last element in elements.
     * Time complexity: O(1).
     * @return The last element.
     * @throws NoSuchElementException If elements is empty.
    */
    @Override public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    /**
     * Add element to the front of elements.
     * Time complexity: O(n).
     * @param element A element. Can be anything.
     * @return True if element was added to elements.
    */
    @Override public boolean addFirst(E element) {
        return add(0, element);
    }

    /**
     * Add element to the end of elements.
     * Time complexity: O(n).
     * @param element A element. Can be anything.
     * @return True if element was added to elements.
    */
    @Override public boolean addLast(E element) {
        return add(size, element);
    }

    /**
     * Removes the first element in elements.
     * Time complexity: O(n).
     * @return True if element was removed from elements.
    */
    @Override public E removeFirst() {
        if (isEmpty() == true) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    /**
     * Removes the last element in elements.
     * Time complexity: O(n).
     * @return True if element was removed from elements.
    */
    @Override public E removeLast() {
        if (isEmpty() == true) {
            throw new NoSuchElementException();
        }
        return remove(size - 1);
    }

    /**
     * Adds every element within the collection to the elements.
     * Time complexity: O(n).
    */
    @Override public boolean addAll(Collection<E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    }

    /**
     * Keeps only the elements in the collection and removes the rest.
     * Time complexity: O(n).
     * @param collection A collection containing elements.
    */
    @Override public boolean retainAll(Collection<E> collection) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!(collection.contains(elements[i]))) {
                remove(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes the elements that are in the collection and keeps the rest.
     * Time complexity: O(n).
     * @param collection A collection containing elements.
    */
    @Override public boolean removeAll(Collection<E> collection) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (collection.contains(elements[i])) {
                remove(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Checks if two ArrayLists have the same size and has the same value at the same location.
     * Time complexity: O(n).
     * @param object An object. Can be anything, but should be a ArrayList.
    */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object object) {
        ArrayList<E> other = (ArrayList<E>) object;
        if (this.size != other.size) {
            return false;
        }

        for (int i = 0; i < this.size; i++) {
            if (!(this.elements[i].equals(other.elements[i]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the index of a element that has the exact same value starting from the top of elements.
     * Time complexity: O(n).
     * @param object An object. Can be anything.
    */
    @Override public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the index of a element that has the exact same value starting from the bottom of elements.
     * Time complexity: O(n).
     * @param object An object. Can be anything.
    */
    @Override public int lastIndexOf(Object object) {
        for (int i = (size - 1); i >= 0; i--) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts the ArrayList.
     * Time complexity: O(n log n).
     * @param comparator A comparator. Used for sorting.
    */
    @Override public void sort(Comparator<E> comparator) {
        PriorityQueue<E> pq;
        if (comparator != null) {
            pq = new PriorityQueue<>(comparator);
        } else {
            pq = new PriorityQueue<>();
        }

        int index = 0;
        while ((elements[index] != null) && (index < size)) {
            pq.offer(elements[index++]);
        }

        clear();
        while (!(pq.isEmpty())) {
            add(pq.poll());
        }
    }

    /**
     * Clones the ArrayList.
     * Time complexity: O(n).
    */
    @SuppressWarnings("unchecked")
    @Override public Object clone() {
        ArrayList<E> clonedList = new ArrayList<>();
        clonedList.size = this.size;
        clonedList.elements = (E[]) new Object[this.size];

        for (int i = 0; i < this.size; i++) {
            clonedList.elements[i] = this.elements[i];
        }
        return clonedList;
    }

    /**
     * Turns ArrayList into a Array.
     * Time complexity: O(n).
    */
    @Override public Object[] toArray() {
        Object[] tempArray = new Object[size];
        for (int i = 0; i < size; i++) {
            tempArray[i] = elements[i];
        }
        return tempArray;
    }
}