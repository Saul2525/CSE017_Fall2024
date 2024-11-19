/*
    Saul Toribio
    11/21/24
    CSE017 Fall 2024: ALA 9
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for testing PriorityQueue and TreeSet.
 * 
 * According to the results, the offer and poll methods of the PriorityQueue demonstrate
 * better performance on average (having a O(log n) for both offer and poll)
 * compared to the add and remove methods of the TreeSet (having a O(log n) in average cases, while having O(n) in worst cases).
 * This is due to the efficiency of PriorityQueue compared to the overhead of TreeSet.
 * 
 * In both case scenarios with random and sorted, the heap significantly outperformed the BST.
 * This is again due to hemps being structured based on priority. While the BST is efficient
 * in searching, insertion, and deletion of values, BST falters due to how the data was inserted, specifically sorted data.
*/
public class AnimalTree {
    public static void main(String[] args) {
        PriorityQueue<String> animalHeap = new PriorityQueue<>();
        TreeSet<String> animalBST = new TreeSet<>();
        ArrayList<String> al = new ArrayList<>();

        try (Scanner readFile = new Scanner(new File("animals.txt"))) {
            int count = 0, totalBST = 0, totalHeap = 0;
            System.out.println("Testing the add and offer methods");
            System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal", "BST add", "Heap offer");
            while (readFile.hasNextLine()) {
                String animal = readFile.nextLine();
                animalBST.add(animal);
                animalHeap.offer(animal);
                al.add(animal);

                if ((count % 25) == 0) {
                    System.out.printf("%-30s\t%-10d\t%-10d\n", animal, TreeSet.addIterations, PriorityQueue.offerIterations);
                }
                count++;
                totalBST += TreeSet.addIterations;
                totalHeap += PriorityQueue.offerIterations;
            }
            System.out.printf("%-30s\t%-10d\t%-10d\n\n", "Average", (totalBST / al.size()), (totalHeap / al.size()));
        } catch (FileNotFoundException e) {
            System.out.println("animals.txt not found.");
        }

        int totalBST = 0, totalHeap = 0;
        System.out.println("Testing the remove and poll methods");
        System.out.printf("%-30s\t%-10s\t%-30s\t%-10s\n", "Average", "BST remove", "Average", "Heap poll");
        for (int i = 0; i < 20; i++) {
            int index = (int) (Math.random() * al.size());
            String animal = al.get(index);

            animalBST.remove(animal);
            String heapAnimal = animalHeap.poll();

            System.out.printf("%-30s\t%-10d\t%-30s\t%-10d\n", animal, TreeSet.removeIterations, heapAnimal, PriorityQueue.pollIterations);
            totalBST += TreeSet.removeIterations;
            totalHeap += PriorityQueue.pollIterations;
        }
        System.out.printf("%-30s\t%-10d\t%-30s\t%-10d\n\n", "Average", (totalBST / 20), "Average", (totalHeap / 20));

        System.out.println("Properties of the two binary trees with random data");
        System.out.println("Height of the BST: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());
        System.out.println("Height of the Heap: " + animalHeap.height());
        System.out.println("Heap is balanced? " + animalHeap.isBalanced());

        al.sort(null);
        animalBST.clear();
        animalHeap.clear();
        for (String animal : al) {
            animalBST.add(animal);
            animalHeap.offer(animal);
        }
        System.out.println();

        System.out.println("Properties of the two binary trees with sorted data");
        System.out.println("Height of the BST: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());
        System.out.println("Height of the Heap: " + animalHeap.height());
        System.out.println("Heap is balanced? " + animalHeap.isBalanced());
    }
}