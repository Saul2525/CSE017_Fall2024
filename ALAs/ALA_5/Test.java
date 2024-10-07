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

public class Test {
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

    public static void stateOperations() {
        ArrayList<Pair<String, String>> states = new ArrayList<>();
        readStates(states, "states.txt");

        Scanner keyboard = new Scanner(System.in);
        int operation = 0;

        do {
            printMenu();
            operation = Integer.parseInt(keyboard.nextLine());

            switch (operation) {
                case 1:
                    print(states);
                    break;
                case 2:
                    System.out.println("Enter a state:");
                    String state = keyboard.nextLine();
                    int index = search(states, state);

                    if (index != -1) {
                        System.out.println("State found: " + states.get(index));
                    } else {
                        System.out.println("State not found.");
                    }
                    break;
                case 3:
                    states.sort(new ComparatorByFirst<>());
                    break;
                case 4:
                    states.sort(new ComparatorBySecond<>());
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    keyboard.close();
                    break;
                default:
                    System.out.println("Invalid operation.");
                    break;
            }
        } while (operation != 5);
    }

    public static void printMenu() {
        System.out.println("Select an operation:");
        System.out.println("1: View the list of states");
        System.out.println("2: Search states by name");
        System.out.println("3: Sort states by name");
        System.out.println("4: Sort states by capital");
        System.out.println("5: Exit");
    }
    
    public static <E1, E2> void print(ArrayList<Pair<E1, E2>> list) {
        for (Pair <E1, E2> p : list) {
            System.out.println(p);
        }
    }

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

    public static void main(String[] args) {
        String type = args[0];
        
        if (type.equals("states")) {
            stateOperations();
        } else if (type.equals("trees")) {
            ArrayList<Pair<String, String>> trees = new ArrayList<>();
        } else {
            System.out.println("Invalid data type.");
        }
    }
}