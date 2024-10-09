/*
    Saul Toribio
    10/3/24
    CSE017 Fall 2024: ALA 5
    IDE: VSCode; JDK: 11
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A class that uses generic classes, interfaces, and methods to sort and print two .txt files.
*/
public class Test {
    /**
     * Returns the index of a the state or tree object.
     * @param <E1> A generic type.
     * @param <E2> Another generic type.
     * @param list The array of state or tree objects.
     * @param key The state or tree object supposedly in the list.
     * @return The index of the state or tree object.
    */
    public static <E1, E2> int search(ArrayList<Pair<E1, E2>> list, E1 key) {
        for (int i = 0; i < list.size(); i++) {
            Pair<E1, E2> p = list.get(i);
            E1 first = p.getFirst();

            if (first.equals(key)) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * Prints the entire array of state or tree objects.
     * @param <E1> A generic type.
     * @param <E2> Another generic type.
     * @param list The array of state or tree objects.
    */
    public static <E1, E2> void print(ArrayList<Pair<E1, E2>> list) {
        for (Pair <E1, E2> p : list) {
            System.out.println(p);
        }
        System.out.println();
    }

    /**
     * The menu for when a user inputs "states" in args.
    */
    public static void statesOperations() {
        ArrayList<Pair<String, String>> states = new ArrayList<>();
        readStates(states, "states.txt");

        Scanner keyboard = new Scanner(System.in);
        int operation = 0;

        do {
            System.out.println("Select an operation:");
            System.out.println("1: View the list of states");
            System.out.println("2: Search states by name");
            System.out.println("3: Sort states by name");
            System.out.println("4: Sort states by capital");
            System.out.println("5: Exit");
            operation = Integer.parseInt(keyboard.nextLine());

            switch (operation) {
                case 1:
                    print(states);
                    break;
                case 2:
                    System.out.println("\nEnter a state:");
                    String state = keyboard.nextLine();
                    int index = search(states, state);

                    if (index != -1) {
                        System.out.println("State found: " + states.get(index));
                    } else {
                        System.out.println("No state found.");
                    }
                    System.out.println();
                    break;
                case 3:
                    states.sort(new ComparatorByFirst<>());
                    System.out.println();
                    break;
                case 4:
                    states.sort(new ComparatorBySecond<>());
                    System.out.println();
                    break;
                case 5:
                    keyboard.close();
                    break;
                default:
                    System.out.println("Invalid operation.");
                    break;
            }
        } while (operation != 5);
    }

    /**
     * Used to add state objects to the state array.
     * @param list The state array.
     * @param filename The name of the .txt file.
    */
    public static void readStates(ArrayList<Pair<String, String>> list, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));

            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] items = line.split("\\|");
                Pair<String, String> state = new Pair<>(items[0], items[1]);

                list.add(state);
            }

            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * The menu for when a user inputs "trees" in args.
    */
    public static void treesOperations() {
        ArrayList<Pair<String, Integer>> trees = new ArrayList<>();
        readTrees(trees, "trees.txt");

        Scanner keyboard = new Scanner(System.in);
        int operation = 0;

        do {
            System.out.println("Select an operation:");
            System.out.println("1: View the list of trees");
            System.out.println("2: Search trees by name");
            System.out.println("3: Sort trees by name");
            System.out.println("4: Sort trees by height");
            System.out.println("5: Exit");
            operation = Integer.parseInt(keyboard.nextLine());

            switch (operation) {
                case 1:
                    print(trees);
                    break;
                case 2:
                    System.out.println("\nEnter a tree name:");
                    String tree = keyboard.nextLine();
                    int index = search(trees, tree);

                    if (index != -1) {
                        System.out.println("Tree found: " + trees.get(index));
                    } else {
                        System.out.println("No tree found.");
                    }
                    System.out.println();
                    break;
                case 3:
                    trees.sort(new ComparatorByFirst<>());
                    System.out.println();
                    break;
                case 4:
                    trees.sort(new ComparatorBySecond<>());
                    System.out.println();
                    break;
                case 5:
                    keyboard.close();
                    break;
                default:
                    System.out.println("Invalid operation.");
                    break;
            }
        } while (operation != 5);
    }

    /**
     * Used to add tree objects to the tree array.
     * @param list The tree array.
     * @param filename The name of the .txt file.
    */
    public static void readTrees(ArrayList<Pair<String, Integer>> list, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));

            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] items = line.split("\\|");
                Pair<String, Integer> tree = new Pair<>(items[0], Integer.parseInt(items[1]));

                list.add(tree);
            }

            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static void main(String[] args) {
        String type = args[0];
        
        if (type.equals("states")) {
            statesOperations();
        } else if (type.equals("trees")) {
            treesOperations();
        } else {
            System.out.println("Invalid data type. states and trees are the only types available.");
        }
    }
}