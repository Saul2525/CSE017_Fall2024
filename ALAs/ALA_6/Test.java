/*
    Saul Toribio
    10/10/24
    CSE017 Fall 2024: ALA 6
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        boolean runProgram = true;
        do {
            try {
                Stack<Integer> postfixStack = new Stack<>();
                System.out.println("Enter a postfix expression:");
                String postfix = scnr.nextLine();
                String[] tokens = postfix.split(" ");

                for (String token : tokens) {
                    if (token.matches("\\d{1,}")) {
                        int operand = Integer.parseInt(token);
                        postfixStack.push(operand);
                    } else {
                        int operand1 = postfixStack.pop();
                        int operand2 = postfixStack.pop();

                        switch (token) {
                            case "+":
                                postfixStack.push(operand1 + operand2);
                                break;
                            case "-":
                                postfixStack.push(operand2 - operand1);
                                break;
                            case "*":
                                postfixStack.push(operand1 * operand2);
                                break;
                            case "/":
                                postfixStack.push(operand2 / operand1);
                                break;
                            default:
                                System.out.println("Invalid operation");
                                break;
                        }
                    }
                }

                int result = postfixStack.pop();
                if (!(postfixStack.isEmpty())) {
                    System.out.println("Malformed expression.");
                } else {
                    System.out.printf("Result: %d\n", result);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Malformed expression.");
            }

            System.out.println("Do you want to evaluate another postfix expression? (Yes/No):");
            String selection = scnr.nextLine();
            switch (selection) {
                case "Yes":
                case "yes":
                case "Y":
                case "y":
                    break;
                case "No":
                case "no":
                case "N":
                case "n":
                    runProgram = false;
                    break;
            }
        } while (runProgram == true);

        PriorityQueue<PrintRequest> printingQueue = new PriorityQueue<>();
        try {
            Scanner readFile = new Scanner(new File("requests.txt"));
            while (readFile.hasNext()) {
                int id = readFile.nextInt();
                String group = readFile.next();
                long size = readFile.nextLong();

                PrintRequest pr = new PrintRequest(group, size, id);
                printingQueue.offer(pr);
            }
            readFile.close();

            long totalTime = 0;
            System.out.println("\nSummary of the printed requests:");
            System.out.println("-------------------------------------------------------------");
            System.out.println("User ID\t\tGroup\t\tSize\t\tCompletion Time");

            while (!printingQueue.isEmpty()) {
                PrintRequest pr = printingQueue.poll();
                long time = pr.getSize() / 10000;
                totalTime += time;
                System.out.printf("%s\t%s\n", pr, formatTime(time));
            }

            System.out.println("\nTotal Printing Time: " + formatTime(totalTime));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        scnr.close();
    }

    public static String formatTime(long time) {
        long hours = (time / 3600000);
        long minutes = ((time % 3600000) / 60000);
        long seconds = ((time % 60000) / 1000);
        long milliseconds = (time % 1000);

        return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds);
    }
}