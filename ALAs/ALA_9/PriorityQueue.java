/*
    Saul Toribio
    11/21/24
    CSE017 Fall 2024: ALA 9
    IDE: VSCode; JDK: 11
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A generic class that contains a private ArrayList and Comparator.
*/
public class PriorityQueue<E> {
    public static int offerIterations, pollIterations;
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
     * @param value A generic value.
    */
    @SuppressWarnings("unchecked")
    public void offer(E value) {
        offerIterations = 0;
        list.add(value);
        int currentIndex = (list.size() - 1);
        while (currentIndex > 0) {
            offerIterations++;
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
        pollIterations = 0;
        if (list.isEmpty()) {
            return null;
        }
        E removedItem = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            pollIterations++;
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

    /**
     * The main method of height.
     * Time complexity: O(n).
     * @return The height of the PriorityQueue's ArrayList.
    */
    public int height() {
        return height(0);
    }

    /**
     * The helper method of height.
     * Time complexity: O(n).
     * @param nodeIndex The current height.
     * @return The height of the PriorityQueue's ArrayList.
    */
    private int height(int nodeIndex) {
        if (nodeIndex >= size()) {
            return 0;
        }
        if ((((nodeIndex * 2) + 1) >= size()) && (((nodeIndex * 2) + 2) >= size())) {
            return 1;
        }
        return (1 + Math.max(height((nodeIndex * 2) + 1), height((nodeIndex * 2) + 2)));
    }

    /**
     * The main method of isBalanced.
     * Time complexity: O(n^2).
     * @return True if the height difference is within bounds and the left and right subtrees are balanced.
    */
    public boolean isBalanced() {
        return isBalanced(0);
    }

    /**
     * The main method of isBalanced.
     * Time complexity: O(n^2).
     * @param nodeIndex The current height.
     * @return True if the height difference is within bounds and the left and right subtrees are balanced.
    */
    private boolean isBalanced(int nodeIndex) {
        if (nodeIndex >= size()) {
            return true;
        }

        int hL = height((nodeIndex * 2) + 1);
        int hR = height((nodeIndex * 2) + 2);
        if (Math.abs(hL - hR) > 1) {
            return false;
        }

        return (isBalanced((nodeIndex * 2) + 1) && isBalanced((nodeIndex * 2) + 2));
    }
}