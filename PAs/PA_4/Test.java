/*
    Saul Toribio
    10/7/24
    CSE017 Fall 2024: PA 4
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that uses recursive methods to find and print files and their sizes, as well as finding words within .txt files across multiple different floors.
*/
public class Test {
    /**
     * Gets the size of a single file or the sum of a entire directory, represented as a long.
     * The time complexity of getSize() can be expressed as O(t), where t is the total number of files and directories.
     * @param dir The file or directory.
     * @return The size of the file or directory.
    */
    public static long getSize(File dir) {
        if (dir.isFile()) {
            return dir.length();
        } else if (dir.isDirectory()) {
            long totalSize = 0;

            for (File file : dir.listFiles()) {
                totalSize += getSize(file);
            }

            return totalSize;
        }

        return -1;
    }

    /**
     * Formats the size of the file or directory to be a String.
     * @param size The size of the file or directory.
     * @return The formatted size represented as a String.
    */
    public static String formatSize(long size) {
        String[] unit = {"bytes", "Kbytes", "Mbytes", "Gbytes"};
        int unitIndex = 0;

        double formattedSize = size;
        while ((formattedSize >= 1024) && (unitIndex < unit.length)) {
            formattedSize /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", formattedSize, unit[unitIndex]);
    }

    /**
     * Prints the name and size of the files inside of the directory.
     * @param dir The file or directory.
    */
    public static void printSize(File dir) {
        System.out.printf("%s%10s\n", "Filename/folder", "Size");
        System.out.println("========================================");

        if (dir.getName().equals(".")) {
            System.out.printf("\n%-20s %s\n", "empty/", "0 bytes");
        } else {
            for (File file : dir.listFiles()) {
                System.out.printf("%-20s %s\n", file.getName(), formatSize(getSize(file)));
            }
        }
    }

    /**
     * The main method of tree.
     * @param folder The directory.
    */
    public static void tree(File folder) {
        tree(folder, 0);
    }

    /**
     * The helper method of tree. Prints out the subdirectories and files of the main directory.
     * The time complexity of tree() and its helper method can be expressed as O(t), where t is the total number of files and directories.
     * @param folder The directory.
     * @param depth How far a branch of a folder is.
    */
    public static void tree(File folder, int depth) {
        for (File file : folder.listFiles()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("\t");
            }

            if (file.isDirectory()) {
                System.out.printf("%s/\n", file.getName());
                tree(file, depth + 1);
            } else if (file.isFile()) {
                System.out.printf("%s\n", file.getName());
            }
        }
    }

    /**
     * Searchs .txt files for a words that contain an inputted word.
     * The time complexity of findWord() can be expressed as O(t * l * w), where t is the total number of files across the directories,
     * l is the average amount of lines per .txt file, and w is the average amount of words per line.
     * @param folder The file or directory.
     * @param word The word inputted. Checks if a word in the .txt file contains this word.
    */
    public static void findWord(File folder, String word) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                findWord(file, word);
            }
        } else if (folder.isFile()) {
            try {
                Scanner readFile = new Scanner(new File(folder.getPath()));
                int count = 0;

                while (readFile.hasNextLine()) {
                    String[] line = readFile.nextLine().split("\\W+");
                    for (String phrase : line) {
                        if (phrase.contains(word)) {
                            count++;
                        }
                    }
                }

                if (count > 0) {
                    System.out.printf("\"%s\" appears %d times in %s\n", word, count, folder.getPath());
                }
                readFile.close();
            } catch (FileNotFoundException e) {
            }
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        boolean runProgram = true;
        while (runProgram) {
            System.out.println("Select an option:\n1. Print Size\n2. Tree\n3. Find Word\n4. Exit");
            String selection = scnr.nextLine();

            switch (selection) {
                case "1":
                    System.out.println("Enter the path of the directory:");
                    File dir = new File(scnr.nextLine());

                    if (!(dir.exists())) {
                        System.out.printf("Error: %s does not exist.\n", dir.getName());
                    } else {
                        printSize(dir);
                    }

                    System.out.println();
                    break;
                case "2":
                    System.out.println("Enter the path of the directory:");
                    File folder = new File(scnr.nextLine());

                    if (!(folder.exists())) {
                        System.out.printf("Error: %s does not exist.\n", folder.getName());
                    } else {
                        tree(folder);
                    }

                    System.out.println();
                    break;
                case "3":
                    System.out.println("Enter the path of the directory:");
                    File tempFolder = new File(scnr.nextLine());

                    if (!(tempFolder.exists())) {
                        System.out.printf("Error: %s does not exist.\n", tempFolder.getName());
                        System.out.println();
                        break;
                    }

                    System.out.println("Enter the word to search for:");
                    String word = scnr.nextLine();

                    findWord(tempFolder, word);
                    System.out.println();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    runProgram = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    break;
            }
        }

        scnr.close();
    }
}