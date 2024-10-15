/*
    Saul Toribio
    10/10/24
    CSE017 Fall 2024: ALA 6
    IDE: VSCode; JDK: 11
*/

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Stack<Integer> postfixStack = new Stack<>();

        try {
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
                System.out.println("Malformed expression");
            } else {
                System.out.printf("Result = %d\n", result);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Malformed expression");
        }

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

            while (!printingQueue.isEmpty()) {
                PrintRequest pr = printingQueue.poll();
                long time = pr.getSize() / 10000;
                System.out.printf("%s\t%d\n", pr, time);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        scnr.close();
    }
}