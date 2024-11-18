/*
    Saul Toribio
    11/11/24
    CSE017 Fall 2024: PA 7
    IDE: VSCode; JDK: 11
*/

import java.util.Comparator;
import java.util.ListIterator;

/**
 * A interface that extends Collection to create a List.
*/
public interface List<E> extends Collection<E> {
    public boolean add(int index, E element);
    public E get(int index);
    public int indexOf(Object o);
    public int lastIndexOf(Object o);
    public E remove(int index);
    public E set(int index, E element);
    public void sort(Comparator<E> c);
    public boolean addFirst(E e);
    public boolean addLast(E e);
    public E getFirst();
    public E getLast();
    public ListIterator<E> listIterator();
    public ListIterator<E> listIterator(int i);
    public E removeFirst();
    public E removeLast();
}