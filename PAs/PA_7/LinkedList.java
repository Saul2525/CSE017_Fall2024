/*
    Saul Toribio
    11/11/24
    CSE017 Fall 2024: PA 7
    IDE: VSCode; JDK: 11
*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A generic class that implements Cloneable and List to create a LinkedList.
*/
public class LinkedList<E> implements Cloneable, List<E> {
    private Node head, tail;
    private int size;

    /**
     * The class that defines what Node is.
    */
    private class Node {
        public Node previous, next;
        public E value;

        /**
         * Default configuration of a Node.
         * @param value A generic value. Can be anything.
        */
        public Node(E value) {
            this.value = value;
        }
    }

    /**
     * Default configuration of a LinkedList.
     * Time complexity: O(1).
    */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Time complexity: O(1).
     * @return The size of the LinkedList.
    */
    public int size() {
        return size;
    }

    /**
     * Clears the LinkedList.
     * Time complexity: O(1).
    */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Checks to see if the LinkedList is empty.
     * Time complexity: O(1).
     * @return True if the size of the LinkedList is 0, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Adds a generic element to the LinkedList.
     * Time complexity: O(1).
     * @param element A generic element. Can be anything.
     * @return True if a generic element was added to the LinkedList.
    */
    public boolean add(E element) {
        return addLast(element);
    }

    /**
     * Checks if an object is within the LinkedList.
     * Time complexity: O(n).
     * @param object A object.
     * @return True if the object is within the LinkedList, otherwise false.
    */
    public boolean contains(Object object) {
        for (Node current = head; current != null; current = current.next) {
            if (current.value.equals(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the first generic element in the LinkedList.
     * Time complexity: O(1).
     * @throws NoSuchElementException If the LinkedList is empty.
    */
    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    /**
     * Gets the last generic element in the LinkedList.
     * Time complexity: O(1).
     * @throws NoSuchElementException If the LinkedList is empty.
    */
    public E getLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    /**
     * Time complexity: O(1).
     * @return An LinkedListIterator.
    */
    @Override public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Returns a String containing the contents of LinkedList.
     * Time complexity: O(n).
    */
    public String toString() {
        String output = "[";
        Node current = head;

        while (current.next != null) {
            output += current.value + " ";
            current = current.next;
        }

        output += current.value + "]";
        return output;
    }

    /**
     * A private class that implements ListIterator to iterator through the LinkedList.
    */
    private class LinkedListIterator implements ListIterator<E> {
        private Node current = head;

        /**
         * Unused constructor.
        */
        public LinkedListIterator() {
        }

        /**
         * A constructor used to determine whether or not current should start iterating from the head or from the tale to the specified index.
         * Time complexity: O(n).
         * @param index The location where ListIterator will stop iterating to.
         * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of the LinkedList.
        */
        public LinkedListIterator(int index) {
            if ((index < 0) || (index > size)) {
                throw new ArrayIndexOutOfBoundsException();
            }

            if (index < (size / 2)) {
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            } else {
                current = tail;
                for (int i = (size - 1); i > index; i--) {
                    current = current.previous;
                }
            }
        }

        /**
         * Used to determine if the iterator can move backward to the previous node.
         * Time complexity: O(1).
        */
        public boolean hasPrevious() {
            return (((current == null) && (tail != null)) || ((current != null) && (current.previous != null)));
        }

        /**
         * Used to determine if the iterator can move forward to the next node.
         * Time complexity: O(1).
        */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * Gets the previous value of current, i.e. the value of the node behind the current node.
         * Time complexity: O(1).
         * @throws NoSuchElementException If the list is empty (tail == null) or the current node is the head with nothing previous to iterator to.
        */
        public E previous() {
            if (!(hasPrevious())) {
                throw new NoSuchElementException();
            }

            current = (current == null) ? tail : current.previous;
            return current.value;
        }

        /**
         * Gets the previous value of current, i.e. the value of the node behind the current node.
         * Time complexity: O(1).
         * @throws NoSuchElementException If the current element is equal to null.
        */
        public E next() {
            if (!(hasNext())) {
                throw new NoSuchElementException();
            }

            E value = current.value;
            current = current.next;
            return value;
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
     * Adds a element to the start of the LinkedList.
     * Time complexity: O(1).
     * @param element A generic element. Can be anything.
    */
    @Override public boolean addFirst(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }

        size++;
        return true;
    }

    /**
     * Adds a element to the end of the LinkedList.
     * Time complexity: O(1).
     * @param element A generic element. Can be anything.
    */
    @Override public boolean addLast(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        size++;
        return true;
    }

    /**
     * Adds an element to the LinkedList at a specified index.
     * Time complexity: O(n).
     * @param index The location where the element will be added in the LinkedList.
     * @param element A generic element. Can be anything.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of the LinkedList.
    */
    @Override public boolean add(int index, E element) {
        if ((index < 0) || (index > size)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return addFirst(element);
        } else if (index == size) {
            return addLast(element);
        } else {
            Node current = head, newNode = new Node(element);
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            newNode.previous = current.previous;
            newNode.next = current;
            current.previous.next = newNode;
            current.previous = newNode;
            size++;
            return true;
        }
    }

    /**
     * Adds every element within the collection to the LinkedList.
     * Time complexity: O(n).
     * @param collection A collection containing elements.
    */
    @Override public boolean addAll(Collection<E> collection) {
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            addLast(iterator.next());
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
        for (Node current = head; current != null; current = current.next) {
            if (!(collection.contains(current.value))) {
                remove(current.value);
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
        for (Node current = head; current != null; current = current.next) {
            if (collection.contains(current.value)) {
                remove(current.value);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Checks if two LinkedLists have the same size and has the same value at the same nodes.
     * Time complexity: O(n).
     * @param object An object. Can be anything, but should be a LinkedList.
    */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object object) {
        LinkedList<E> other = (LinkedList<E>) object;
        if (this.size != other.size) {
            return false;
        }

        Node current = this.head, otherCurrent = other.head;
        while (current != null) {
            if (!(current.value.equals(otherCurrent.value))) {
                return false;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        return true;
    }

    /**
     * Gets the value of a Node at the specified index.
     * Time complexity: O(n).
     * @param index The location of the specified Node and its value.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of the LinkedList.
    */
    @Override public E get(int index) {
        if ((index < 0) || (index > size)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Sets the value of a Node at the specified index.
     * Time complexity: O(n).
     * @param index The location of the specified Node and its value.
     * @param element The new value that will replace the old value at the specified index.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of the LinkedList.
    */
    @Override public E set(int index, E element) {
        if ((index < 0) || (index > size)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldValue = current.value;
        current.value = element;
        return oldValue;
    }

    /**
     * Gets the index of a Node that has the exact same value starting from the head.
     * Time complexity: O(n).
     * @param object An object. Can be anything.
    */
    @Override public int indexOf(Object object) {
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            if (current.value.equals(object)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Gets the index of a Node that has the exact same value starting from the tail.
     * Time complexity: O(n).
     * @param object An object. Can be anything.
    */
    @Override public int lastIndexOf(Object object) {
        int index = (size - 1);
        for (Node current = tail; current != null; current = current.previous) {
            if (current.value.equals(object)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    /**
     * Removes the Node at the start of the LinkedList.
     * Time complexity: O(1).
     * @throws NoSuchElementException If LinkedList is empty.
    */
    @Override public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        E value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.previous = null;
        }
        size--;
        return value;
    }

    /**
     * Removes the Node at the end of the LinkedList.
     * Time complexity: O(1).
     * @throws NoSuchElementException If LinkedList is empty.
    */
    @Override public E removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        if (head == tail) {
            return removeFirst();
        }
        E value = tail.value;
        tail = tail.previous;
        tail.next = null;
        size--;
        return value;
    }

    /**
     * Removes a Node that has the same value as object.
     * Time complexity: O(n).
     * @param object An object. Can be anything.
    */
    @Override public boolean remove(Object object) {
        for (Node current = head; current != null; current = current.next) {
            if (current.value.equals(object)) {
                if (current == head) {
                    removeFirst();
                } else if (current == tail) {
                    removeLast();
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes a Node at the specified index.
     * Time complexity: O(n).
     * @param index The location of the specified Node.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of the LinkedList.
    */
    @Override public E remove(int index) {
        if ((index < 0) || (index > size)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == (size - 1)) {
            return removeLast();
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E value = current.value;
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return value;
    }

    /**
     * Time complexity: O(1).
     * @return An LinkedListIterator.
    */
    @Override public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    /**
     * Time complexity: O(1).
     * @param index The index used to decide to whether or not iterate from head or tail.
     * @return An LinkedListIterator.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or greater than the size of the LinkedList.
    */
    @Override public ListIterator<E> listIterator(int index) {
        if ((index < 0) || (index > size)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return new LinkedListIterator(index);
    }

    /**
     * Sorts the LinkedList.
     * Time complexity: O(n^2).
     * @param comparator A comparator. Used for sorting.
    */
    @SuppressWarnings("unchecked")
    @Override public void sort(Comparator<E> comparator) {
        for (Node current = head; current != null; current = current.next) {
            for (Node nextNode = current.next; nextNode != null; nextNode = nextNode.next) {
                if (comparator != null) {
                    if (comparator.compare(nextNode.value, current.value) < 0) {
                        E temp = current.value;
                        current.value = nextNode.value;
                        nextNode.value = temp;
                    }
                } else {
                    if (((Comparable<E>) nextNode.value).compareTo(current.value) < 0) {
                        E temp = current.value;
                        current.value = nextNode.value;
                        nextNode.value = temp;
                    }
                }
            }
        }
    }

    /**
     * Clones the LinkedList.
     * Time complexity: O(n).
    */
    @Override public Object clone() {
        LinkedList<E> clonedList = new LinkedList<>();
        for (Node current = head; current != null; current = current.next) {
            clonedList.addLast(current.value);
        }
        return clonedList;
    }

    /**
     * Turns LinkedList into a Array.
     * Time complexity: O(n).
    */
    @Override public Object[] toArray() {
        Object[] tempArray = new Object[size];
        int index = 0;

        for (Node current = head; current != null; current = current.next) {
            tempArray[index++] = current.value;
        }
        return tempArray;
    }
}