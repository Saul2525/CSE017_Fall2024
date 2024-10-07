/*
    Saul Toribio
    10/3/24
    CSE017 Fall 2024: ALA 5
    IDE: VSCode; JDK: 11
*/

public class Pair<E1, E2> {
    private E1 first;
    private E2 second;

    public Pair(E1 f, E2 s) {
        first = f;
        second = s;
    }

    public E1 getFirst() {
        return first;
    }

    public E2 getSecond() {
        return second;
    }

    public void setFirst(E1 f) {
        first = f;
    }

    public void setSecond(E2 s) {
        second = s;
    }

    public String toString() {
        return String.format("(%s, %s)", first.toString(), second.toString());
    }

    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair<E1, E2> p = (Pair) o;
            return (this.first.equals(p.first) && this.second.equals(p.second));
        }

        return false;
    }
}