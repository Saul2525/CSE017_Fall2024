/*
    Saul Toribio
    10/3/24
    CSE017 Fall 2024: ALA 5
    IDE: VSCode; JDK: 11
*/

import java.util.Comparator;

public class ComparatorBySecond<E1, E2 extends Comparable<E2>> implements Comparator<Pair<E1, E2>> {
    public int compare(Pair<E1, E2> p1, Pair<E1, E2> p2) {
        E2 firstp1 = p1.getSecond();
        E2 firstp2 = p2.getSecond();

        return (firstp1.compareTo(firstp2));
    }
}