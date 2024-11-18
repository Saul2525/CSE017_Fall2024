/*
    Saul Toribio
    11/11/24
    CSE017 Fall 2024: PA 7
    IDE: VSCode; JDK: 11
*/

import java.util.Iterator;

/**
 * A interface that whose methods form Collection.
*/
public interface Collection<E> {
    public boolean add(E element);
    public boolean addAll(Collection<E> c);
    public void clear();
    public boolean contains(Object o);
    public boolean equals(Object o);
    public boolean isEmpty();
    public Iterator<E> iterator();
    public boolean remove(Object o);
    public boolean removeAll(Collection<E> c);
    public boolean retainAll(Collection<E> c);
    public int size();
    public Object[] toArray();
}