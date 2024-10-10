/*
    Saul Toribio
    10/10/24
    CSE017 Fall 2024: ALA 6
    IDE: VSCode; JDK: 11
*/

import java.util.Scanner;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Stack<Integer> postfixStack = new Stack<>();

        System.out.println("Enter a postfix expression:");
        String postfix = scnr.nextLine();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            
        }
    }
}