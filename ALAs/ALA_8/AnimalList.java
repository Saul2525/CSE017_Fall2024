/*
    Saul Toribio
    11/13/24
    CSE017 Fall 2024: ALA 8
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that tests ArrayList and LinkedList.
 * 
 * In this case, contains was evenly matched by both ArrayList and LinkedList.
 * Adding elements tended to either be coin flip by ArrayList and LinkedList. It did seem to be somewhat faster for ArrayList for the most part.
 * However, LinkedList was always the fastest in remove.
 * This isn't surprising because ArrayList has fast random access, while LinkedList has fast insertion and deletion.
*/
public class AnimalList {
    /**
     * A static method that reads and adds animals to the ArrayList and LinkedList.
     * @param al The ArrayList.
     * @param ll The LinkedList.
     * @param filename The name of the file we're reading for information.
     * @throws FileNotFoundException If the file animals.txt is not found.
     */
    public static void readAnimals(ArrayList<String> al, LinkedList<String> ll, String filename) {
        try (Scanner readFile = new Scanner(new File(filename))) {
            while (readFile.hasNextLine()) {
                String animal = readFile.nextLine();
                al.add(animal);
                ll.add(animal);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Tests the method contains() in both ArrayList and LinkedList.
     * @param al The ArrayList.
     * @param ll The LinkedList.
    */
    public static void testContains(ArrayList<String> al, LinkedList<String> ll) {
        int totalAL = 0, totalLL = 0;
        System.out.println("\nComparing the methods contains(Object o)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.contains(randomAnimal);
            ll.contains(randomAnimal);

            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.containsIterations, LinkedList.containsIterations);
            totalAL += ArrayList.containsIterations;
            totalLL += LinkedList.containsIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", (totalAL / 20), (totalLL / 20));
    }

    /**
     * Tests the method add() in both ArrayList and LinkedList.
     * @param al The ArrayList.
     * @param ll The LinkedList.
    */
    public static void testAdd(ArrayList<String> al, LinkedList<String> ll) {
        int totalAL = 0, totalLL = 0;
        System.out.println("\nComparing the methods add(int index, E item)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.add(randomIndex, randomAnimal);
            ll.add(randomIndex, randomAnimal);

            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.addIterations, LinkedList.addIterations);
            totalAL += ArrayList.addIterations;
            totalLL += LinkedList.addIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", (totalAL / 20), (totalLL / 20));
    }

    /**
     * Tests the method remove() in both ArrayList and LinkedList.
     * @param al The ArrayList.
     * @param ll The LinkedList.
    */
    public static void testRemove(ArrayList<String> al, LinkedList<String> ll) {
        int totalAL = 0, totalLL = 0;
        System.out.println("\nComparing the methods remove(Object o)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.remove(randomAnimal);
            ll.remove(randomAnimal);

            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.removeIterations, LinkedList.removeIterations);
            totalAL += ArrayList.removeIterations;
            totalLL += LinkedList.removeIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", (totalAL / 20), (totalLL / 20));
    }

    public static void main(String[] args) {
        ArrayList<String> animalAL = new ArrayList<>();
        LinkedList<String> animalLL = new LinkedList<>();

        readAnimals(animalAL, animalLL, "animals.txt");
        testContains(animalAL, animalLL);
        testAdd(animalAL, animalLL);
        testRemove(animalAL, animalLL);
    }
}