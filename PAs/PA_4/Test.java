/*
    Saul Toribio
    10/7/24
    CSE017 Fall 2024: PA 4
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static long getSize(File dir) {
        if (dir.isFile()) {
            return dir.length();
        } else if (dir.isDirectory()) {
            File[] directory = dir.listFiles();
            long totalSize = 0;

            for (File file : directory) {
                totalSize += getSize(file);
            }

            return totalSize;
        }

        return -1;
    }

    public static String formatSize(long size) {
        String[] unit = {"bytes", "Kbytes", "Mbytes", "Gbytes"};
        int unitIndex = 0;

        double formattedSize = size;
        while ((formattedSize >= 1024) && (unitIndex < (unit.length - 1))) {
            formattedSize /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", size, unit[unitIndex]);
    }

    public static void printSize(File dir) {
        try {
            if (!(dir.exists() && dir.isDirectory())) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Error: %s does not exist.\n", dir.getName());
        }

        System.out.println("Filename/folder\tSize");
        System.out.println("========================================");

        File[] folder = dir.listFiles();
        for (File file : folder) {
            System.out.printf("%s\t\t%s", file.getName(), formatSize(getSize(file)));
        }
    }

    public static void tree(File folder) {
        tree(folder, 0);
    }

    public static void tree(File folder, int depth) {
        File[] dir = folder.listFiles();
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }

        for (File thing : dir) {
            if (thing.isDirectory()) {
                System.out.printf("%s/\n", thing.getName());
                tree(thing, depth++);
            } else if (thing.isFile()) {
                System.out.printf("%s\n", thing.getName());
            }
        }
    }

    public static void findWord(File folder, String word) {
        File[] dir = folder.listFiles();
        for (File thing : dir) {
            if (thing.isDirectory()) {
                findWord(thing, word);
            } else if (thing.isFile()) {
                try {
                    Scanner readFile = new Scanner(new File(thing.getName()));
                    int count = 0;

                    while (readFile.hasNextLine()) {
                        String[] line = readFile.nextLine().split("\\W+");
                        for (String phrase : line) {
                            if (phrase.equals(word)) {
                                count++;
                            }
                        }
                    }

                    if (count != 0) {
                        System.out.printf("\"%s\" appears %d times in %s\n", word, count, thing.getAbsolutePath());
                    }
                    readFile.close();
                } catch (FileNotFoundException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int selection = 0;

        boolean runProgram = true;
        do {
            System.out.println("Select an option:\n1. Print Size\n2. Tree\n3. Find Word\n4. Exit");
            selection = scnr.nextInt();

            String directoryString = "";
            File tempFile = new File("");
            String wordString = "";
            switch (selection) {
                case 1:
                    System.out.println("Enter the path of the directory:");
                    directoryString = scnr.next();

                    tempFile = new File(directoryString);
                    printSize(tempFile);
                    break;
                case 2:
                    System.out.println("Enter the path of the directory:");
                    directoryString = scnr.next();

                    tempFile = new File(directoryString);
                    tree(tempFile);
                    break;
                case 3:
                    System.out.println("Enter the path of the directory:");
                    directoryString = scnr.next();
                    System.out.println("Enter the word to search for:");
                    wordString = scnr.next();

                    tempFile = new File(directoryString);
                    findWord(tempFile, wordString);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    runProgram = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (runProgram == true);

        scnr.close();
    }
}