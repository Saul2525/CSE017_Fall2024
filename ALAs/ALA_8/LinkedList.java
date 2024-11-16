/*
    Saul Toribio
    11/13/24
    CSE017 Fall 2024: ALA 8
    IDE: VSCode; JDK: 11
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that uses Node to store information.
*/
public class LinkedList<E> {
    private Node head, tail;
    private int size;

    public static int containsIterations, removeIterations, addIterations;

    /**
     * The class for Node.
    */
    private class Node {
        Node next;
        E value;

        /**
         * Sets the pointer to next as null and value as initialValue when instantiated.
         * @param initialValue
        */
        Node(E initialValue) {
            next = null;
            value = initialValue;
        }
    }

    /**
     * Default configuration of LinkedList.
    */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Default configuration of add.
     * @param value The value to be added to LinkedList.
     * @return True if value was added to the LinkedList.
    */
    public boolean add(E value) {
        return addLast(value);
    }

    /**
     * Adds a value to the start of the LinkedList.
     * @param value The value to be added to LinkedList.
     * @return True if value was added to the LinkedList.
    */
    public boolean addFirst(E value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }

    /**
     * Adds a value to the end of the LinkedList.
     * @param value The value to be added to LinkedList.
     * @return True if value was added to the LinkedList.
    */
    public boolean addLast(E value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * @return Gets the first value in LinkedList.
    */
    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    /**
     * @return Gets the last value in LinkedList.
    */
    public E getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    /**
     * Removes the first value in LinkedList.
     * @return True if the first value in LinkedList was removed.
    */
    public boolean removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;

        if (head == null) {
            tail = null;
        }
        size--;
        return true;
    }

    /**
     * Removes the last value in LinkedList.
     * @return True if the last value in LinkedList was removed.
    */
    public boolean removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            return removeFirst();
        }
        Node current = head;

        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;
        return true;
    }

    /**
     * Prints the content of LinkedList.
    */
    public String toString() {
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }

    /**
     * Sets the head and tail to null and size to 0.
    */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * @return True if size is equal to 0, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * @return The size of LinkedArray.
    */
    public int size() {
        return size;
    }

    /**
     * @return A LinkedListIterator.
    */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * A class that implements Iterator to traverse the LinkedList.
    */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        /**
         * Checks if current is null.
        */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * Gets the next value and current. Returns value of the new current.
        */
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            E value = current.value;
            current = current.next;
            return value;
        }
    }

    /**
     * Uses a iterator to check whether or not the object is in the LinkedList.
     * Time complexity: O(n).
     * @param o An object.
     * @return True if the LinkedList contains the object, otherwise false.
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
     * Removes a object from the LinkedList.
     * Time complexity: O(n).
     * @param o An object.
     * @return True if the object was removed from the LinkedList, otherwise false.
    */
    public boolean remove(Object o) {
        Node pervious = null;
        Node current = head;
        removeIterations = 0;
        while ((current != null) && (!(current.value.equals(o)))) {
            removeIterations++;
            pervious = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }
        if (pervious == null) {
            return removeFirst();
        }

        pervious.next = current.next;
        size--;
        return true;
    }

    /**
     * Adds a item to the LinkedList at the specified index via Node.
     * Time complexity: O(n).
     * @param index The location for the item to be added in the list.
     * @param value A item.
     * @return True if it was added to the LinkedList at the specified index via Node.
     * @throws ArrayIndexOutOfBoundsException If the index is below 0 or greater then the size of the list.
    */
    public boolean add(int index, E value) {
        if ((index < 0) || (index > size)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return addFirst(value);
        } else if (index == size) {
            return addLast(value);
        }

        addIterations = 0;
        Node pervious = null;
        Node current = head;
        for (int i = 0; i < index; i++) {
            addIterations++;
            pervious = current;
            current = current.next;
        }

        Node tempNode = new Node(value);
        pervious.next = tempNode;
        tempNode.next = current;
        size++;
        return true;
    }
}