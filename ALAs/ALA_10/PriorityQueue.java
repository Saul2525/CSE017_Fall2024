/*
    Saul Toribio
    11/18/24
    CSE017 Fall 2024: PA 8
    IDE: VSCode; JDK: 11
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A generic class that contains a private ArrayList and Comparator.
*/
public class PriorityQueue<E> {
    private ArrayList<E> list;
    private Comparator<E> comparator;

    /**
     * Default configuration of a PriorityQueue.
    */
    public PriorityQueue() {
        list = new ArrayList<>();
        comparator = null;
    }

    /**
     * A PriorityQueue with a specified comparator.
     * @param comparator A comparator. Used for sorting.
    */
    public PriorityQueue(Comparator<E> comparator) {
        list = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * @return The size of the PriorityQueue's ArrayList.
    */
    public int size() {
        return list.size();
    }

    /**
     * @return True if the PriorityQueue's ArrayList contains nothing, otherwise false.
    */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Clears the PriorityQueue's ArrayList.
    */
    public void clear() {
        list.clear();
    }

    /**
     * Prints the content of the PriorityQueue's ArrayList.
    */
    public String toString() {
        return list.toString();
    }

    /**
     * Gets the first value in the PriorityQueue's ArrayList.
     * @return The first value in the PriorityQueue's ArrayList.
     * @throws NoSuchElementException If the PriorityQueue's ArrayList is empty.
    */
    public E peek() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.get(0);
    }

    /**
     * Inserts a new element into a PriorityQueue's ArrayList as a min-heap.
     * @param value
    */
    @SuppressWarnings("unchecked")
    public void offer(E value) {
        list.add(value);
        int currentIndex = (list.size() - 1);
        while (currentIndex > 0) {
            int parentIndex = ((currentIndex - 1) / 2);
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);

            int comparison = (comparator == null) ? ((Comparable<E>) current).compareTo(parent) : comparator.compare(current, parent);
            if (comparison < 0) {
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            } else {
                break;
            }
            currentIndex = parentIndex;
        }
    }

    /**
     * @return And removes PriorityQueue's ArrayList's smallest element.
    */
    @SuppressWarnings("unchecked")
    public E poll() {
        if (list.isEmpty()) {
            return null;
        }
        E removedItem = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int left = (2 * currentIndex + 1);
            int right = (2 * currentIndex + 2);
            if (left >= list.size()) {
                break;
            }

            int minIndex = left;
            E min = list.get(minIndex);
            if (right < list.size()) {
                int comparison = (comparator == null)
                    ? ((Comparable<E>) min).compareTo(list.get(right))
                    : comparator.compare(min, list.get(right));
                
                if (comparison > 0) {
                    minIndex = right;
                }
            }

            E current = list.get(currentIndex);
            min = list.get(minIndex);
            int comparison = (comparator == null) ? ((Comparable<E>) current).compareTo(min) : comparator.compare(current, min);
            if (comparison > 0) {
                list.set(minIndex, current);
                list.set(currentIndex, min);
                currentIndex = minIndex;
            } else {
                break;
            }
        }
        return removedItem;
    }
}