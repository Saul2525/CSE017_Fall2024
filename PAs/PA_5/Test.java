/*
    Saul Toribio
    10/14/24
    CSE017 Fall 2024: PA 5
    IDE: VSCode; JDK: 11
*/

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class for testing rationals.
*/
public class Test {
    /**
     * Prints the list of rationals.
     * @param <E> A element.
     * @param list The list of rational numbers.
    */
    public static <E> void print(ArrayList<E> list) {
        for (E element : list) {
            System.out.println(element);
        }
    }

    /**
     * The main method of search.
     * @param <E> A element.
     * @param list The list of rational numbers.
     * @param key The element requested.
     * @return The index of the element requested.
    */
    public static <E> int search(ArrayList<E> list, E key) {
        return search(list, key, 0);
    }

    /**
     * The helper method of search.
     * @param <E> A element.
     * @param list The list of rational numbers.
     * @param key The element requested.
     * @param index The index of the list.
     * @return The index of the element requested.
    */
    public static <E> int search(ArrayList<E> list, E key, int index) {
        if (index >= list.size()) {
            return -1;
        } else if (list.get(index).equals(key)) {
            return index;
        }
        return search(list, key, index + 1);
    }

    /**
     * Sorts the list in a descending order.
     * @param <E> A element.
     * @param list The list of rational numbers.
     * @param c A rational number.
    */
    public static <E> void sort(ArrayList<E> list, Comparator<E> c) {
        for (int i = 0; i < list.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (c.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            E temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    public static void main(String[] args){
        // Test the class Rational
        System.out.println("\nTest case 1: Arithmetic on Fractions");
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(3, 2);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 +  " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        System.out.println("\nTest case 2: Arithmetic on Fractions");
        r1 = new Rational(2, 3);
        r2 = new Rational(3, 5);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        System.out.println("\nTest case 3: Arithmetic on Fractions");
        r1 = new Rational(1, 4);
        r2 = new Rational(3, 4);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        System.out.println("\nTest case 4: Arithmetic on Fractions");
        r1 = new Rational(4, 8);
        r2 = new Rational(3, 9);
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.sub(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.mult(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.div(r2));

        // Creating a dataset of numbers
        ArrayList<Number> numbers = new ArrayList<>(25);
        for(int i=0; i<5; i++){
            numbers.add(new Rational((int)(Math.random()*10), (int)(Math.random()*9)+1));
        }
        for(int i=0; i<5; i++){
            numbers.add((int)(Math.random()*1000000000));
        }
        for(int i=0; i<5; i++){
            numbers.add((long)(Math.random()*10000000000L));
        }
        for(int i=0; i<5; i++){
            numbers.add(Math.random()*1e20);
        }
        for(int i=0; i<5; i++){
            numbers.add(Math.random()*1e40);
        }
        java.util.Collections.shuffle(numbers);

        // print the list of numbers
        System.out.println("\n\nTest case 5: Generic print method");
        System.out.println("List of Numbers");
        print(numbers);

        // Test Searching for a number
        System.out.println("\n\nTest case 6: Generic Search Method (fail)");
        Number n = new Rational(3,4);
        int index = search(numbers, n);
        if(index == -1){
            System.out.println("Number " + n + " not found.");
        }
        else{
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 7: Generic Search Method (success)");
        n = numbers.get(4);
        index = search(numbers, n);
        if(index == -1){
            System.out.println("Number " + n + " not found.");
        }
        else{
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 8: Generic Search Method (success)");
        n = numbers.get(9);
        index = search(numbers, n);
        if(index == -1){
            System.out.println("Number " + n + " not found.");
        }
        else{
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 9: Generic Search Method (success)");
        n = numbers.get(12);
        index = search(numbers, n);
        if(index == -1){
            System.out.println("Number " + n + " not found.");
        }
        else{
            System.out.println("Number " + n + " found at index " + index);
        }

        System.out.println("\nTest case 10: Generic Search Method (fail)");
        n = 35.75;
        index = search(numbers, n);
        if(index == -1){
            System.out.println("Number " + n + " not found.");
        }
        else{
            System.out.println("Number " + n + " found at index " + index);
        }

        // Test Sorting by double
        System.out.println("\n\nTest case 11: Generic Sort Method with a Comparator");
        sort(numbers, new ComparatorByDouble());
        System.out.println("List of Numbers sorted by their double values");
        print(numbers);
    }
}