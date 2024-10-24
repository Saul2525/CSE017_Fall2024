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
 * A class to test ArrayList<E>.
*/
public class Test {
    public static void main(String[] args) {
        /**
         * A class to compare the length of two Strings.
        */
        class StringComparator implements Comparator<String> {
            public int compare(String s1, String s2) {
                return (s1.length() - s2.length());
            }
        }

        String[] list = {"Apple", "Bread", "Juice", "Salad", "Pizza", "Sandwich", "Soup", "Burger", "Muffin", "Croissant", "Burrito", "Taco"};
        ArrayList<String> food = new ArrayList<>();
        for (String s : list) {
            food.add(s);
        }

        System.out.println("List using the unidirectional iterator:");
        Iterator<String> iter = food.iterator();
        System.out.print("[");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println("]\n");

        System.out.println("List using the bidirectional iterator (forward):");
        ListIterator<String> liter = food.listIterator();
        System.out.print("[");
        while (liter.hasNext()) {
            System.out.print(liter.next() + " ");
        }
        System.out.println("]\n");

        System.out.println("List using the bidirectional iterator (backward):");
        liter = food.listIterator(food.size());
        System.out.print("[");
        while (liter.hasPrevious()) {
            System.out.print(liter.previous() + " ");
        }
        System.out.println("]\n");

        System.out.println("Sorted list using compareTo:");
        food.sort();
        System.out.println(food);
        System.out.println();

        System.out.println("Shuffled list:");
        food.shuffle();
        System.out.println(food);
        System.out.println();

        System.out.println("Sorted list using compare:");
        food.sort(new StringComparator());
        System.out.println(food);
    }
}