/*
    Saul Toribio
    9/19/24
    CSE017 Fall 2024: ALA 4
    IDE: VSCode; JDK: 11
*/

import java.util.Scanner;

/**
 * A class that implements recursive methods to count the amount of times a character appears in a string or swap
 * characters around in a string and prints them out.
*/
public class Recursion {
    /**
     * A method that uses substring to recursively count the amount of times a character appears in a string.
     * @param str The user's inputted string.
     * @param c The user's inputted char.
    */
    public static int count(String str, char c) {
        if (str.length() == 0) {
            return 0;
        }

        if (str.charAt(0) == c) {
            return (count(str.substring(1), c) + 1);
        } else {
            return count(str.substring(1), c);
        }
    }
    
    /**
     * The main method for permutations. Calls the helper method.
     * @param s The user's inputted string.
    */
    public static void permutations(String s) {
        permutations("", s);
    }

    /**
     * The helper method for permutations.
     * @param s1 A string that is recursively built.
     * @param s2 The user's inputted string.
    */
    public static void permutations(String s1, String s2) {
        if (s2.length() == 0) {
            System.out.println(s1);
            return;
        }

        for (int i = 0; i < s2.length(); i++) {
            String sx = s1 + s2.charAt(i);
            String sy = s2.substring(0, i) + s2.substring(i + 1);
            
            permutations(sx, sy);
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter a string:");
        String str = scnr.next();
        System.out.println("Enter a character:");
        char c = scnr.next().charAt(0);

        System.out.printf("%c appears %d times in \"%s\"\n", c, count(str, c), str);

        System.out.println("Enter a string:");
        str = scnr.next();

        permutations(str);

        scnr.close();
    }
}