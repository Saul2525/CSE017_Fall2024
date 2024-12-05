/*
    Saul Toribio
    12/2/24
    CSE017 Fall 2024: ALA 10
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to test the speeds of LinkedList, TreeSet, and HashMap.
 * 
 * Based off the testing, HashMap blows both TreeSet and LinkedList out of the water.
 * This is because Heaps are designed to efficiently maintain the max or min element at the root.
 * Their are optimized for quick access to the highest or lowest priority element and does not require traversing the entire structure to find the max/min.
 * 
 * The collisions() method goes through each spot in the HashMap to find the one with the most items.
 * It keeps track of the largest number and returns it, showing how many elements ended up in the busiest spot due to collisions.
*/
public class Test {
    /**
     * Tests the add/put methods of the HashMap, TreeSet, and LinkedList.
     * @param al A ArrayList.
     * @param hash A HashMap.
     * @param bst A TreeSet.
     * @param ll A LinkedList.
    */
    public static void testAdd(ArrayList<MapEntry<String, String>> al, HashMap<String, String> hash, TreeSet<String> bst, LinkedList<String> ll) {
        int index = 0;
        int totalLL = 0, totalHash = 0, totalBST = 0;

        System.out.println("Testing the add method");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL add", "BST add", "HashMap put");

        for (MapEntry<String, String> entry : al) {
            hash.put(entry.getKey(), entry.getValue());
            bst.add(entry.getKey());
            ll.add(entry.getKey());

            totalLL += LinkedList.addIterations;
            totalBST += TreeSet.addIterations;
            totalHash += HashMap.putIterations;

            if ((index % 1500) == 0) {
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", entry.getKey(), LinkedList.addIterations, TreeSet.addIterations, HashMap.putIterations);
            }
            index++;
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", (totalLL / al.size()), (totalBST / al.size()), (totalHash / al.size()));
    }

    /**
     * Tests the contains/get methods of the HashMap, TreeSet, and LinkedList.
     * @param al A ArrayList.
     * @param hash A HashMap.
     * @param bst A TreeSet.
     * @param ll A LinkedList.
    */
    public static void testContains(ArrayList<MapEntry<String, String>> al, HashMap<String, String> hash, TreeSet<String> bst, LinkedList<String> ll) {
        int index = 0;
        int totalLL = 0, totalHash = 0, totalBST = 0;

        System.out.println("Testing the search method");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL search", "BST search", "HashMap search");

        for (int i = 0; i < 1000; i++) {
            int j = (int) (Math.random() * al.size());
            String word = al.get(j).getKey();

            hash.get(word);
            bst.contains(word);
            ll.contains(word);

            totalLL += LinkedList.containsIterations;
            totalBST += TreeSet.containsIterations;
            totalHash += HashMap.getIterations;

            if ((index % 50) == 0) {
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.containsIterations, TreeSet.containsIterations, HashMap.getIterations);
            }
            index++;
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", (totalLL / 1000), (totalBST / 1000), (totalHash / 1000));
    }

    /**
     * Tests the remove methods of the HashMap, TreeSet, and LinkedList.
     * @param al A ArrayList.
     * @param hash A HashMap.
     * @param bst A TreeSet.
     * @param ll A LinkedList.
    */
    public static void testRemove(ArrayList<MapEntry<String, String>> al, HashMap<String, String> hash, TreeSet<String> bst, LinkedList<String> ll) {
        int index = 0;
        int totalLL = 0, totalHash = 0, totalBST = 0;

        System.out.println("Testing the remove method");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL remove", "BST remove", "HashMap remove");

        for (int i = 0; i < 1000; i++) {
            int j = (int) (Math.random() * al.size());
            String word = al.get(j).getKey();

            hash.remove(word);
            bst.remove(word);
            ll.remove(word);

            totalLL += LinkedList.removeIterations;
            totalBST += TreeSet.removeIterations;
            totalHash += HashMap.removeIterations;

            if ((index % 50) == 0) {
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.removeIterations, TreeSet.removeIterations, HashMap.removeIterations);
            }
            index++;
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", (totalLL / 1000), (totalBST / 1000), (totalHash / 1000));
    }

    public static void main(String[] args) {
        HashMap<String, String> hashWords = new HashMap<>(50000);
        try (Scanner readFile = new Scanner(new File("dictionary.txt"))) {
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] token = line.split("\\|");
                hashWords.put(token[0], token[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        LinkedList<String> llWords = new LinkedList<>();
        TreeSet<String> bstWords = new TreeSet<>();

        ArrayList<MapEntry<String, String>> arrayList = hashWords.toList();
        hashWords.clear();

        testAdd(arrayList, hashWords, bstWords, llWords);
        System.out.println();

        testContains(arrayList, hashWords, bstWords, llWords);
        System.out.println();

        testRemove(arrayList, hashWords, bstWords, llWords);
        System.out.println("\nMaximum number of collisions: " + hashWords.collisions());
    }
}